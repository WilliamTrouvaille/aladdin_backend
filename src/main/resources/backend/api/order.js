function getOrderDetailPage(params) {
    return $axios({
        url: '/sales/page',
        // url: 'json/sales.json',
        method: 'get',
        params
    })
}

// function getOrderDetailPage(params) {
//     return $axios({
//         url: '/backend/json/sales.json',
//         method: 'get',
//     })
// }
