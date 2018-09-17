// vars/kubeDeploy.groovy
def call(imageName, imageTag) {
    def label = "kubectl"
    def podYaml = libraryResource 'podtemplates/kubeDeploy.yml'
    def deployYaml = libraryResource 'k8s/basicDeploy.yml'
    def repoName = env.IMAGE_REPO.toLowerCase()
    podTemplate(name: 'kubectl', label: label, namespace: 'cb-deploy',  yaml: podYaml) {
      node(label) {
        writeFile file: "deploy.yml", text: deployYaml
        container("kubectl") {
          sh("sed -i.bak 's#REPLACE_IMAGE_TAG#946759952272.dkr.ecr.us-east-1.amazonaws.com/${repoName}/${imageName}:${BUILD_NUMBER}#' deploy.yml")
          sh("sed -i.bak 's#REPLACE_SERVICE_NAME#${repoName}#' deploy.yml")
          sh "kubectl --namespace=cb-deploy apply -f deploy.yml"
          sh "echo 'deployed to https://prod.workshop.beedemo.net/${repoName}/'"
        }
      }
    }
}
