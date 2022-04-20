# 前提条件
- `OpenShift 4.9` がインストールされていること

## 事前準備

### 1. GiteaのOperator設定
以下コマンドでプロジェクトを作成します。 
`oc new-project git`
以下コマンドでGiteaをOperatorに追加します。
`oc apply -f https://raw.githubusercontent.com/redhat-gpte-devopsautomation/gitea-operator/master/catalog_source.yaml`

### 2. Operatorのインストール
ダッシュボードから以下のOperatorをインストールします。
- Red Hat OpenShift GitOps
- Red Hat OpenShift Pipelines
- Red Hat CodeReady Workspaces
- Gitea Operator

### 4.
### 3. Giteaのデプロイ 
以下のコマンドでデプロイします。
```
cat << EOF | oc create -n git -f -
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
EOF
```
xxxからフォークします。

cloneして書き換えます
- apimanager.yamlのwildcarddomain
    `oc get ingresses.config/cluster -o jsonpath={.spec.domain}`

### 4. cloneして書き換え


