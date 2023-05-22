function getOrderDetailPage(params) {
    return $axios({
        url: '/sales/page',
        // url: 'json/sales.json',
        method: 'get',
        params
    })
}

// const orderDistribute = (id) => {
//     return $axios({
//         url: `/sales/distribute/${id}`,
//         method: 'get'
//     })
// }

function orderDistribute(data) {
    return $axios({
        'url': `/sales/distribute/${data.id}`,
        'method': 'get',
    })
}

// function getOrderDetailPage(params) {
//     return $axios({
//         url: '/backend/json/sales.json',
//         method: 'get',
//     })
// }
