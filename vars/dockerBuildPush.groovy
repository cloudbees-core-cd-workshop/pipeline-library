// vars/dockerBuildPush.groovy
def call(String imageName, String imageTag = env.BUILD_NUMBER, String target = ".", String dockerFile="Dockerfile", Closure body) {
  def dockerReg = "946759952272.dkr.ecr.us-east-1.amazonaws.com"
  setECRLifecyclePolicy("${env.IMAGE_REPO}/${imageNamae}")
  def label = "kaniko"
  def podYaml = libraryResource 'podtemplates/dockerBuildPush.yml'
  podTemplate(name: 'kaniko', label: label, namespace: 'kaniko',  yaml: podYaml) {
    node(label) {
      container(name: 'kaniko', shell: '/busybox/sh') {
        body()
        withEnv(['PATH+EXTRA=/busybox:/kaniko']) {
          sh """#!/busybox/sh
            executor -f ${pwd()}/${dockerFile} -c ${pwd()} --build-arg buildNumber=${BUILD_NUMBER} -d ${dockerReg}/${env.IMAGE_REPO}/${imageNamae}:${imageTag}
          """
        }
        }
      }
    }
}
