// vars/setECRLifecyclePolicy.groovy
def call(String repoName) {
  repoName = repoName.toLowerCase()
  def label = "aws-cli"
  def podYaml = libraryResource 'podtemplates/awsCli.yml'
  podTemplate(name: 'aws-cli', label: label, yaml: podYaml) {
    node(label) {
      container('aws-cli') {
        try {
          errorMsg = sh(returnStdout: true, script: "aws ecr create-repository --region us-east-1 --repository-name ${repoName} | tr -d '\n'")
          def lifecyclePolicy = libraryResource 'aws/ecr/lifecycle-policy/tempImagePolicy.json'
          sh """aws ecr put-lifecycle-policy --region us-east-1 --repository-name ${repoName} --lifecycle-policy-text '$lifecyclePolicy'"""
        } catch(e) {
          //error other than for RepositoryAlreadyExistsException
          if(!errorMsg.contains("RepositoryAlreadyExistsException")) {
            throw e
          }
        }
      }
    }
  }
}
