//will set an environmental variable to short commit, must of git repo in workspace
def call(size) {
    size = size ?: 7
    def SHORT_COMMIT = sh(returnStdout: true, script: "git rev-parse HEAD | cut -c1-${size} | tr -d '\n'")
    def commit = sh(returnStdout: true, script: 'git rev-parse HEAD')
    def author = sh(returnStdout: true, script: "git --no-pager show -s --format='%an' ${commit} | tr -d '\n'")
    sh "echo ${SHORT_COMMIT}"
    sh "echo ${author}"
    env.SHORT_COMMIT = SHORT_COMMIT
    env.COMMIT_AUTHOR = author
}
