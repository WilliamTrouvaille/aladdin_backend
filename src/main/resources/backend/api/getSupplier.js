// 查询供应商接口
function getSupplier() {
    return $axios({
        url: '/supplier.json',
        method: 'get'
    });
}
