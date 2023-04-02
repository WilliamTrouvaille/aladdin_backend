function getSupplierList(params) {
    return $axios({
        url: '/supplier/page',
        method: 'get',
        params
    })
}

// function getSupplierList(params){
//     return $axios({
//         url: '/json/supplier.json',
//         method: 'get',
//         params
//     })
// }

function addSupplier(params) {
    return $axios({
        url: '/supplier',
        method: 'post',
        data: {...params}
    })
}

function querySupplierById(id) {
    return $axios({
        url: `/supplier/${id}`,
        method: 'get'
    })
}

function editSupplier(params) {
    return $axios({
        url: '/supplier',
        method: 'put',
        data: {...params}
    })
}