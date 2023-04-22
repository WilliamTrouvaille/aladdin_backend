function getMemberList(params) {
    return $axios({
        url: '/employee/page',
        // url: '/json/member.json',
        method: 'get',
        params
    })
}


// const enableOrDisableEmployee = (params) => {
//     return $axios({
//         url: '/employee/status/${params.status}',
//         method: 'get',
//         data: {...params}
//     })
// }

// 修改---启用禁用接口
const enableOrDisableEmployee = (params) => {
    return $axios({
        url: `/employee/status`,
        method: 'put',
        params: {...params}
    })
}

// 新增---添加员工
function addEmployee(params) {
    return $axios({
        url: '/employee',
        method: 'post',
        data: {...params}
    })
}

// 修改---添加员工
function editEmployee(params) {
    return $axios({
        url: '/employee',
        method: 'put',
        data: {...params}
    })
}

// 修改页面反查详情接口
function queryEmployeeById(id) {
    return $axios({
        url: `/employee/${id}`,
        method: 'get'
    })
}

// 查询所属门店接口
function getStoreList() {
    return $axios({
        url: `/store`,
        method: 'get'
    })
}

// 查询所属门店接口
function getStoreListTest() {
    return $axios({
        url: `/store/list`,
        method: 'get'
    })
}
