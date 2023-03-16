function getStorePage(params) {
    return $axios({
        url: '/store/page',
        method: 'get',
        params
    })
}


// 新增---添加员工
function addStore(params) {
    return $axios({
        url: '/store/save',
        method: 'post',
        data: {...params}
    })
}

// 修改---添加员工
function editStore(params) {
    return $axios({
        url: '/store',
        method: 'put',
        data: {...params}
    })
}

// 修改页面反查详情接口
function queryStoreById(id) {
    return $axios({
        url: `/store/${id}`,
        method: 'get'
    })
}