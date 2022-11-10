
# Integration Boot Camp

=======
# フォルダ構成
- application (アプリケーションを格納)
  - backend (バックエンドアプリケーションを格納)
  - frontend (フロントエンドアプリケーションを格納)
- cluster (argocdで利用するファイルを格納)

# 前提条件
- `OpenShift 4.10` がインストールされていること  

## 事前準備

### 1. Web Terminalのインストール
ダッシュボードから`Web Terminal`をインストールする

### 2. GiteaのOperator設定
1.  以下コマンドでプロジェクトを作成します。  
`oc new-project git` 

2. 以下コマンドでGiteaをOperatorHubに追加します。  
```
oc apply -f - <<EOF
  apiVersion: operators.coreos.com/v1alpha1
  kind: CatalogSource
  metadata:
    name: redhat-gpte-gitea
    namespace: openshift-marketplace
  spec:
    sourceType: grpc
    image: quay.io/gpte-devops-automation/gitea-catalog:latest
    displayName: Red Hat GPTE (Gitea)
    publisher: Red Hat GPTE
EOF
```

### 3. Operatorのインストール
ダッシュボードから以下のOperatorをインストールします。 
- Red Hat OpenShift GitOps
- Red Hat OpenShift Pipelines
- Red Hat CodeReady Workspaces
- Gitea Operator

### 4. Giteaのデプロイ (約5分)
以下のコマンドでデプロイします。 
```
oc create -f - << EOF
  apiVersion: gpte.opentlc.com/v1
  kind: Gitea
  metadata:
    name: gitea
    namespace: git
  spec:
    giteaSsl: true
    giteaAdminUser: opentlc-mgr
    giteaAdminPassword: ""
    giteaAdminPasswordLength: 32
    giteaAdminEmail: test@example.com
    giteaCreateUsers: true
    giteaGenerateUserFormat: "lab-user"
    giteaUserNumber: 1
    giteaUserPassword: openshift
    giteaMigrateRepositories: true
    giteaRepositoriesList:
    - repo: https://github.com/Tanakan/openshift-cluster.git
      name: openshift-cluster
      private: false
EOF
```

以下のコマンドで`"Successful"`が返ってくれば成功  
`oc get giteas.gpte.opentlc.com gitea -n git -o json | jq .status.conditions[0].reason`

### 5. Workspacesのデプロイ (約10分)
停止しないように設定必要
```
cat << EOF | oc create -f -
    apiVersion: org.eclipse.che/v1
    kind: CheCluster
    metadata:
      name: codeready-workspaces
      namespace: openshift-workspaces
    spec:
      metrics:
          enable: true
      server:
          allowUserDefinedWorkspaceNamespaces: false
          cheClusterRoles: ''
          cheFlavor: codeready
          cheWorkspaceClusterRole: ''
          gitSelfSignedCert: false
          nonProxyHosts: ''
          proxyPassword: ''
          proxyPort: ''
          proxyURL: ''
          proxyUser: ''
          serverExposureStrategy: ''
          serverTrustStoreConfigMapName: ''
          tlsSupport: true
          workspaceNamespaceDefault: <username>-codeready
      auth:
          externalIdentityProvider: false
          identityProviderAdminUserName: ''
          identityProviderClientId: ''
          identityProviderPassword: ''
          identityProviderRealm: ''
          identityProviderURL: ''
          initialOpenShiftOAuthUser: true
          oAuthClientName: ''
          oAuthSecret: ''
      database:
          chePostgresDb: ''
          chePostgresHostName: ''
          chePostgresPassword: ''
          chePostgresPort: ''
          chePostgresUser: ''
          externalDb: false
      storage:
          postgresPVCStorageClassName: ''
          preCreateSubPaths: true
          pvcClaimSize: 10Gi
          pvcStrategy: common
          workspacePVCStorageClassName: ''
EOF
```
以下コマンドで`Available`が返ってくれば成功  
`oc get checluster -n openshift-workspaces codeready-workspaces -o json | jq .status.cheClusterRunning` 

### 6. Gitの設定
以下コマンドでHOSTを確認  
`oc get route gitea -n git`  
以下URLでログイン  
`https://<確認したHOST>`   
username: `lab-user`   
password: `openshift` 

cloneして以下を書き換えたあと、pushする。
- `cluster/apimanager.yaml`の`<wildcardDomain>`を以下の値に書き換える  
  `oc get ingresses.config/cluster -o json | jq -r .spec.domain`
  

### 7. GitOpsの設定 
権限付与する  
`oc adm policy add-cluster-role-to-user edit system:serviceaccount:openshift-gitops:openshift-gitops-argocd-application-controller`

ログインする  
`oc extract secret/openshift-gitops-cluster --to=- -n openshift-gitops`

### 8. Middlewareのデプロイ (約5分)
<GiteaURL>`を以下に値に書き換える
`https://<hostname>/lab-user/openshift-cluster`
<hostname>は`oc get route gitea -n git`取得する
```
cat << EOF | oc create -f -
  apiVersion: argoproj.io/v1alpha1
  kind: Application
  metadata:
    name: cluster
    namespace: openshift-gitops
  spec:
    destination:
      server: https://kubernetes.default.svc
    project: default
    source:
      path: manifest/overlays/prod
      repoURL: <GiteaURL>
      targetRevision: HEAD
    syncPolicy:
      automated: {}
EOF
```

### 9. デモアプリのデプロイ (Tekton)
- WebApp
  - 社食アプリ(React)  
      Public client
  - 社内決済利用確認アプリ(React)  
      Public client
  - Healthcare Manage(React)  
      Confidential

### 10. 3scaleの設定
- OIDCで連携する設定
- Policy(Scope)の設定
  まずは設定しないで疎通確認  
  https://access.redhat.com/documentation/ja-jp/red_hat_3scale_api_management/2.7/html-single/administering_the_api_gateway/index#_configuring_zync_que_to_use_custom_ca_certificates

### 11. Keycloakの設定
各WebアプリにClientIDやらを設定する
