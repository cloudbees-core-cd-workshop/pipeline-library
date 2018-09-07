//imageNameTag.groovy
def call(body) {
  tokens = "${env.JOB_NAME}".tokenize('/')
  env.IMAGE_NAME = tokens[tokens.size()-3] + "/" + tokens[tokens.size()-2]
  env.IMAGE_TAG = tokens[tokens.size()-1]
}
