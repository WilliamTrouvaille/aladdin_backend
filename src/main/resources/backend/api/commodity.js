// 查询列表接口

function getCommodityPage(params) {
    return $axios({
        url: `/commodity/page`,
        // url: `/json/commodity.json`,
        method: 'get',
        params
    })
}

// function getCommodityPage(params) {
//     return $axios({
//         url: `/json/commodity.json`,
//         method: 'get',
//         params
//     })
// }

function getSupplier() {
    return $axios({
        url: `/supplier/list`,
        method: 'get',
    })
}

// function getSupplier() {
//     return $axios({
//         url: `/json/supplier.json`,
//         method: 'get',
//     })
// }

// 删除接口
const deleteCommodity = (ids) => {
    return $axios({
        url: '/commodity',
        method: 'delete',
        params: {ids}
    })
}

// 修改接口
const editCommodity = (params) => {
    return $axios({
        url: '/commodity',
        method: 'put',
        data: {...params}
    })
}

// 新增接口
const addCommodity = (params) => {
    return $axios({
        url: '/commodity',
        method: 'post',
        data: {...params}
    })
}

// 查询详情
const queryCommodityById = (id) => {
    return $axios({
        url: `/commodity/${id}`,
        method: 'get'
    })
}


// 查商品列表的接口
const queryCommodityList = (params) => {
    return $axios({
        url: '/commodity/list',
        method: 'get',
        params
    })
}

// 文件down预览
const commonDownload = (params) => {
    return $axios({
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        url: '/common/download',
        method: 'get',
        params
    })
}

// 起售停售---批量起售停售接口
const CommodityStatusByStatus = (params) => {
    return $axios({
        url: `/commodity/status/${params.status}`,
        method: 'post',
        params: {ids: params.id}
    })
}
