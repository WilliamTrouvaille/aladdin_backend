function getUserList(params) {
    return $axios({
        url: '/user/page',
        // url: '/json/user.json',
        method: 'get',
        params
    })
}

// function enableOrDisableUser(params) {
//     return $axios({
//         url: '/user',
//         method: 'put',
//         data: {...params}
//     })
// }

const updatteUserPassword = (params) => {
    return $axios({
        url: `/user/updatePwd`,
        method: 'post',
        params: {...params}
    })
}


const enableOrDisableUser = (params) => {
    return $axios({
        url: `/user/status`,
        method: 'put',
        params: {...params}
    })
}

function addUser(params) {
    return $axios({
        url: '/user',
        method: 'post',
        data: {...params}
    })
}

// 修改---添加员工
function editUser(params) {
    return $axios({
        url: '/user',
        method: 'put',
        data: {...params}
    })
}

// 修改页面反查详情接口
function queryUserById(id) {
    return $axios({
        url: `/user/${id}`,
        method: 'get'
    })
}
