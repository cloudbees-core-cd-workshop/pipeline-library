//will set an environmental variable to short commit, must of git repo in workspace
def call(size) {
    size = size ?: 7
    def shortCommit = sh(returnStdout: true, script: "git rev-parse HEAD | cut -c1-${size} | tr -d '\n'")
    def author = sh(returnStdout: true, script: "git --no-pager show -s --format='%an' ${SHORT_COMMIT} | tr -d '\n'")
    sh "echo ${shortCommit}"
    sh "echo ${author}"
    env.SHORT_COMMIT = shortCommit
    env.COMMIT_AUTHOR = author
}
