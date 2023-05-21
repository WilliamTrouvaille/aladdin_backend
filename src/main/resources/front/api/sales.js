//提交订单
function addOrderApi(data) {
    return $axios({
        'url': '/sales/submit',
        'method': 'post',
        data
    })
}

//查询所有订单
function orderListApi() {
    return $axios({
        'url': '/sales/list',
        'method': 'get',
    })
}

//分页查询订单
function orderPagingApi(data) {
    return $axios({
        'url': '/sales/userPage',
        'method': 'get',
        params: {...data}
    })
}

//再来一单
function orderAgainApi(data) {
    return $axios({
        'url': `/sales/again/${data.id}`,
        'method': 'get',
    })
}

// 取消订单
function cancelOrderApi(data) {
    return $axios({
        'url': `/sales/cancel/${data.id}`,
        'method': 'get',
    })
}