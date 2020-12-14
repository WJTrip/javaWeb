function fillProvince() {
    $.ajax({
        type:"post",
        url:"queryProvinceCity",
        data:{},
        dataType:"json",
        success:function (response) {
            let provinceElement=document.getElementById("provinceId");
            //清除select的所有option
            provinceElement.options.length=0;
            //增加一个option
            provinceElement.add(new Option("请选择省份",""));
            //循环添加所有option
            for(let i=0;i<response.length;i++){
                provinceElement.add(new Option(response[i].provinceName,
                    response[i].provinceId));
            }
        }
    });
}

/**
 * 省份下拉框选择发生改变事件：
 * 清空城市下拉框选项，增加默认提示项（请选择城市）
 * 检查是否选择了省份，没有选择则给出错误提示并返回
 * 否则就清楚错误提示信息，查询备选这省份对应的城市信息，增加到城市下拉框的选择列表中
 */
let provinceError=$("#provinceError");
let CityId=$("#cityId").val();

let fillCity=function (cityId) {

    let provinceId=$("#provinceId").val();
    if(provinceId===""){//检查是否选择省份
        provinceError.css("color"," #c00202");
        provinceError.text("请选择省份！");
        return;
    }
    province_correct=true;
    let city_correct=false;
    provinceError.text("");
    CityId.empty();
    CityId.append($("<option>").val("").text("请选择城市"));
    $.ajax({
       type:"post",
       url:"queryProvinceCity",
       data:{ provinceId : provinceId },
       dataType:"json",
       success:function (response) {
            for(let i=0;i<response.length;i++){
                let option=$("<option>").val(response[i].cityId).text(response[i].cityName);
                CityId.append(option);
            }
            CityId.val(cityId);
            if(CityId.val()!=="")
                city_correct=true;
       }
    });
};

/**
 * 检查用户名是否合法/存在
 */

let userNameError=$("#userNameError");
let userName_correct=false;
function checkUser() {
    if($("#userName").val()==="") {
        userNameError.css("color", " #c00202");
        userNameError.text("用户名不能为空！");
        return;
    }

    //检查用户名是否合法
    if (/^[a-zA-Z][a-zA-Z\d]{3,14}$/.test(this.value) === false) {
        userNameError.css("color", " #c00202");
        userNameError.text("用户名只能使用英文字母和数字，以字母开头，长度为4到15个字符");
        return;
    }

    $.ajax({
       type:"post",
       url:"checkExist",
       data:{userName:$(this).val()},
       dataType:"json",
       success:function (response) {
            if(response.code===0){//用户名字符合法且不存在
                userNameError.css("color","green");
                userNameError.text("改用户名可用！");
                userName_correct=true;
            }else{//用户名字符合法但已存在
                userNameError.css("color"," #c00202");
                userNameError.text("该用户名已存在！");
            }
       }
    });
}

/**
 * 检查邮箱是否合法
 */
let mailError=$("#mailError");
let mail_correct=false;
let mail=$("#mail").val();
function checkMailFormat() {
    mailError.text("");
    if(mail.val()===""){
        mailError.css("color"," #c00202");
        mailError.text("邮箱不能为空！");
        mail_correct=false;
        return false;
    }

    //检查邮箱是否合法
    if (/^[a-zA-Z0-9]+([._\\]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(mail.val()) === false) {
        mailError.css("color", " #c00202");
        mailError.text("邮箱格式不对！");
        mail_correct = false;
        return false;
    }
    mail_correct=true;
    return true;
}

/**
 * 检查邮箱是否存在
 */

function checkMailExit() {
    $.ajax({
       type:"post",
       url:"checkExist",
        data:{mail:mail.val()},
        dataType:"json",
        success:function (response) {
            if(response.code===0){//邮箱字符合法且可以使用
                mailError.css("color","green");
                mailError.text("该邮箱可用！");
                mail_correct=true;
            }
            else{
                mailError.css("color"," #c00202");
                mailError.text("该邮箱已被使用！");
                mail_correct=false;
            }
        }
    });
}

function checkMail() {
    if (checkMailFormat()){//先检查邮箱格式是否合法再检查是否存在
        checkMailExit();
    }
}

let provinceId=$("#provinceId").val();
let province_correct=false;

$(document).ready(function () {
    fillProvince();//调用函数，填充省份下拉框
    provinceId.change(fillCity);//绑定省份下拉框变化事件
    //绑定省份下拉框离开事件
    provinceId.blur(function (e) {
        if($(this).val()===""){
            provinceError.css("color"," #c00202");
            provinceError.text("请选择省份！");
        }
        else{
            provinceError.text("");
            province_correct=true;
        }
    });

    /**
     * 绑定城市下拉框选择项变化事件：检查是否选择了城市
     */
    let city_correct=false;
    let cityError=$("#cityError");
    CityId.blur(function (e) {
        if(CityId.val()===""){
            cityError.css("color"," #c00202");
            cityError.text("请选择城市！");
        }
        else{
            cityError.text("");
            city_correct=true;
        }
    });

    //绑定用户名输入框离开事件
    $('#userName').blur(checkUser);

    /**
     * 真实姓名输入框离开事件
     * 使用正则表达式表达式检查输入是否符合要求（必须为中文，长度2-4）
     */
    let chrNameError=$("#chrNameError");
    let chrName_correct=false;
    $('#chrName').blur(function(event) {
        if ($(this).val() === "") {
            chrNameError.css("color", " #c00202");
            chrNameError.text("真实姓名不能为空");
            return;
        }
        if (/^[\u4e00-\u9fa5]{2,4}$/.test(this.value) === false) {
            chrNameError.css("color", " #c00202");
            chrNameError.text("真实姓名只能使用中文，长度为2到4个字符");
        } else {
            chrName_correct = true;
            $("#chrNameError").text("");
        }
    });
    /**
     * 邮箱地址输入框离开事件
     * (1)使用正则表达式表达式检查输入是否符合要求
     * (2)使用ajax检查邮箱地址是否已存在
     */
    $("#mail").blur(checkMail);

    //密码输入框离开事件：
    let passWord=$("#password").val();
    let passwordError=$("#passwordError");
    let password_correct=false;
    passWord.blur(function() {
        const password_min_length = 3;
        if ($("#password").val().length >= password_min_length) {
            passwordError.css("color", "green");
            passwordError.text("密码设置成功");
            password_correct = true;
        } else {
            passwordError.css("color", " #c00202");
            passwordError.text("密码长度至少为3");
        }
    });

    //确认密码离开事件

    let passWord1=$("#password1").val();
    let passWord1Error=$("#password1Error");
    let password1_correct=false;
    passWord1.blur(function() {
        const password_min_length = 3;
        if (passWord.val() === passWord1.val() && passWord.val().length >= password_min_length) {
            passWord1Error.css("color", "green");
            passWord1Error.text("密码设置成功");
            password1_correct = true;
        } else {
            passWord1Error.css("color", "#c00202");
            passWord1Error.text("密码不一致或长度不够");
        }
    });

    /**
     * 注册按钮点击事件
     */
    $("#btLogin").click(function(e) {
        if (userName_correct & mail_correct && chrName_correct && province_correct && city_correct && password_correct && password1_correct) {
            $.ajax({
                type: "post",
                url: "Register",
                data: $("#registerForm").serialize(), //将表单内容序列化成一个URL 编码字符串
                dataType: "json",
                success: function(response) {
                    alert(response.info);
                    if (response.code === 0) {
                        if ($("#action").val() !== "") {
                            CloseDiv('MyDiv', 'fade');
                            reload();
                        } else {
                            window.location.href = "login.html";
                        }
                    }
                }
            });
        } else {
            $("#userName").blur();
            $('#chrName').blur();
            $("#mail").blur();
            $("#password").blur();
            $("#password1").blur();
            $("#provinceId").blur();
            $("#cityId").blur();
        }
    });
});