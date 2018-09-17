//imageNameTag.groovy
def call(body) {
  tokens = "${env.JOB_NAME.toLowerCase()}".tokenize('/')
  env.IMAGE_REPO = tokens[tokens.size()-3]
  env.IMAGE_NAME = tokens[tokens.size()-2]
  env.IMAGE_TAG = tokens[tokens.size()-1]
}
