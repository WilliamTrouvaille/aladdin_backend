function isValidUsername(str) {
    return ['admin', 'editor'].indexOf(str.trim()) >= 0;
}

function isExternal(path) {
    return /^(https?:|mailto:|tel:)/.test(path);
}

function isCellPhone(val) {
    if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
        return false
    } else {
        return true
    }
}

//校验账号
function checkUserName(rule, value, callback) {
    if (value == "") {
        callback(new Error("请输入账号"))
    } else if (value.length > 20 || value.length < 3) {
        callback(new Error("账号长度应是3-20"))
    } else {
        callback()
    }
}

//校验姓名
function checkName(rule, value, callback) {
    if (value == "") {
        callback(new Error("请输入姓名"))
    } else if (value.length > 12) {
        callback(new Error("账号长度应是1-12"))
    } else {
        callback()
    }
}

//校验姓名
function checkSupplierName(rule, value, callback) {
    if (value === "") {
        callback(new Error("请输入供应商名称"))
    } else if (value.length > 21) {
        callback(new Error("供应商名称过长"))
    } else {
        callback()
    }
}

function checkPhone(rule, value, callback) {
    // let phoneReg = /(^1[3|4|5|6|7|8|9]\d{9}$)|(^09\d{8}$)/;
    if (value === "") {
        callback(new Error("请输入手机号"))
    } else if (!isCellPhone(value)) {//引入methods中封装的检查手机格式的方法
        callback(new Error("请输入正确的手机号!"))
    } else {
        callback()
    }
}


function validID(rule, value, callback) {
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
    if (value == '') {
        callback(new Error('请输入身份证号码'))
    } else if (reg.test(value)) {
        callback()
    } else {
        callback(new Error('身份证号码不正确'))
    }
}

function checkContact(rule, value, callback) {
    if (value == '') {
        callback(new Error('请输入联系人'))
    }
}

function checkAddress(rule, value, callback) {
    if (value == '') {
        callback(new Error('请输入联系地址'))
    }
}


function checkStoreName(rule, value, callback) {
    if (value == '') {
        callback(new Error('请输入门店名称'))
    }
}


function checkPrice(rules, value, callback) {
    if (!value) {
        callback(new Error('请填写商品价格'))
    } else {
        const reg = /^\d+(\.\d{0,2})?$/
        if (reg.test(value)) {
            if (value < 10000) {
                callback()
            } else {
                callback(new Error('商品价格应小于10000'))
            }
        } else {
            callback(new Error('商品价格格式只能为数字,且最多保留两位小数'))
        }
    }
}

function checkPurchasePrice(rules, value, callback) {
    if (!value) {
        callback(new Error('请填写商品进货价'))
    } else {
        const reg = /^\d+(\.\d{0,2})?$/
        if (reg.test(value)) {
            if (value < 1000000) {
                callback()
            } else {
                callback(new Error('商品进货价应小于1000000'))
            }
        } else {
            callback(new Error('商品价格格式只能为数字,且最多保留两位小数'))
        }
    }
}

function checkDiscount(rules, value, callback) {
    if (!value) {
        callback(new Error('请填写商品折扣价'))
    } else {
        const reg = /^\d+(\.\d{0,2})?$/
        if (reg.test(value)) {
            if (value < 10000) {
                callback()
            } else {
                callback(new Error('商品折扣价应小于10000'))
            }
        } else {
            callback(new Error('商品折扣价格式只能为数字,且最多保留两位小数'))
        }
    }
}

// 商品条形码正则校验/^69\d{11}$/ 
// 6902538004045
function checkCode1(rules, value, callback) {
    if (!value) {
        callback(new Error('请填写商品条形码'))
    } else {
        const reg = /69+\d{11}/g
        if (reg.test(value)) {
            callback()
        } else {
            callback(new Error('请输入正确的商品条形码!'))
        }
    }
}

// 规格正则校验
function checkSpecification(rules, value, callback) {
    if (!value) {
        callback(new Error('请填写规格'))
    } else {
        const reg = /\d{0,4}.{0,3}/[\u4e00 - \u9fa5] / i
        if (reg.test(value)) {
            callback()
        } else {
            callback(new Error('请输入正确的规格!'))
        }
    }
}

// 名称正则校验
function checkCommodity(rules, value, callback) {
    if (!value) {
        callback(new Error('请填写商品名称'))
    } else {
        const reg = /[\u4e00-\u9fa5]{0,20}/i
        if (reg.test(value)) {
            callback()
        } else {
            callback(new Error('请输入正确的商品名称!'))
        }
    }
}

// 入库数量
function checkNumber(rules, value, callback) {
    if (!value) {
        callback(new Error('请填写入库数量'))
    } else {
        const reg = /\d{1,5}/i
        if (reg.test(value)) {
            callback()
        } else {
            callback(new Error('请输入正确的入库数量!'))
        }
    }
}

function checkPoducePlace(rules, value, callback) {
    if (!value) {
        callback(new Error('请填写产地'))
    } else {
        const reg = /[\u4e00-\u9fa5]{0,20}/i
        if (reg.test(value)) {
            callback()
        } else {
            callback(new Error('正确的产地应仅含中文,请重试!'))
        }
    }
}