// vars/kubeDeploy.groovy
def call(imageName, imageRepo, imageTag) {
    def label = "kubectl"
    def podYaml = libraryResource 'podtemplates/kubeDeploy.yml'
    def deployYaml = libraryResource 'k8s/basicDeploy.yml'
    writeFile file: "deploy.yml", text: deployYaml
    podTemplate(name: 'kubectl', label: label, namespace: 'cb-deploy',  yaml: podYaml) {
      node(label) {
        container("kubectl") {
          sh("sed -i.bak 's#REPLACE_IMAGE_TAG#946759952272.dkr.ecr.us-east-1.amazonaws.com/${imageRepo}/${imageName}:${imageTag}#' deploy.yml")
          sh("sed -i.bak 's#REPLACE_SERVICE_NAME#${imageRepo}-${imageName}#' deploy.yml")
          sh "kubectl --namespace=cb-deploy apply -f deploy.yml"
        }
      }
    }
}
