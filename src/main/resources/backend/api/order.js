function getOrderDetailPage(params) {
    return $axios({
        url: '/sales/page',
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