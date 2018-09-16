//will set an environmental variable to short commit, must of git repo in workspace
def call(size) {
    size = size ?: 7
    env.SHORT_COMMIT = sh(returnStdout: true, script: "git rev-parse HEAD | cut -c1-${size} | tr -d '\n'")
    env.COMMIT_AUTHOR = sh(returnStdout: true, script: "git --no-pager show -s --format='%an' ${SHORT_COMMIT} | tr -d '\n'")
    sh "echo ${SHORT_COMMIT}"
    sh "echo ${COMMIT_AUTHOR}"
}
