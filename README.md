# 前提条件
- `OpenShift 4.9` がインストールされていること
(4.10だと`NFS Provisioner Operator`がないため動作しない)

## 事前準備

### 1. GiteaのOperator設定
以下コマンドでプロジェクトを作成します。  
`oc new-project git` 
以下コマンドでGiteaをOperatorHubに追加します。 
`oc apply -f https://raw.githubusercontent.com/redhat-gpte-devopsautomation/gitea-operator/master/catalog_source.yaml`

### 2. Operatorのインストール
ダッシュボードから以下のOperatorをインストールします。 
- Red Hat OpenShift GitOps
- Red Hat OpenShift Pipelines
- Red Hat CodeReady Workspaces
- Gitea Operator

### 3. Giteaのデプロイ (約5分)
以下のコマンドでデプロイします。 
```
cat << EOF | oc create -f -
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
      giteaAdminEmail: opentlc-mgr@redhat.com
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


### 4. Workspacesのデプロイ (約10分)
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

### 5. Gitの設定
以下コマンドでHOSTを確認
`oc get route gitea -n git`
以下URLでログイン
`https://<確認したHOST>` 
username: `lab-user` 
password: `openshift` 


cloneして書き換えます。 
`apimanager.yaml`の`wildcarddomain`を以下の値に書き換える
`oc get ingresses.config/cluster -o json | jq -r .spec.domain`
commitする。 
 
### 6. GitOpsの設定 
権限付与
`oc adm policy add-cluster-role-to-user edit system:serviceaccount:openshift-gitops:openshift-gitops-argocd-application-controller`

ログインする
`oc extract secret/openshift-gitops-cluster --to=- -n openshift-gitops`

### 7. Middlewareのデプロイ (約5分)
- cluster
  `argocd/cluster/cluster.yaml`をコピペしてアプリケーションを作る
- middleware
- `argocd/middleware/middleware.yaml`をコピペしてアプリケーションを作る 


### 8. デモアプリのデプロイ (Tekton)
- WebApp
  - 社食アプリ(SpringBoot)
      Confidential
  - 社内決済利用確認アプリ(React)
      Confidential
  - Healthcare Manager (SSR)
      Public client

### 9. 3scaleの設定
- OIDCで連携する設定
- Policy(Scope)の設定
  まずは設定しないで疎通確認

### 10. Keycloakの設定
各WebアプリにClientIDやらを設定する
