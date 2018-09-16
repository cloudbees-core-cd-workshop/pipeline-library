// vars/defineProps.groovy
def call(String file, Map defaults) {
  //defaults from job name for Docker image
  imageNameTag()
  //set SHORT_COMMIT for all defineProps calls
  gitShortCommit()
  //use the Pipeline Utility Steps plugin readProperties step to read the .nodejs-app custom marker file 
  def props = readProperties defaults: defaults, file: file
  for ( e in props ) {
    env.setProperty(e.key, e.value)
  }
}
