function loginApi(data) {
    return $axios({
        'url': '/user/login', 'method': 'post', timeout: 3 * 60 * 1000, data
    })
}

function loginoutApi() {
    return $axios({
        'url': '/user/loginout', 'method': 'post', timeout: 3 * 60 * 1000,
    })
}

function sendMsgApi(data) {
    return $axios({
        'url': '/user/sendMsg', 'method': 'post', timeout: 3 * 60 * 1000, data
    })
}