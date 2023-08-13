<template>
    <div class="login">
        <custom-header :title="type == 'login' ? '登录' : '注册'" :back="'/home'" :burger="'/visitor'"></custom-header>
        <img class="logo" v-if="type=='login'" src="../../public/login.jpg" alt="">
        <img class="logo" v-else src="../../public/register.jpg" alt="">
        <div v-if="type == 'login'" class="login-body login">
            <van-form @submit="onSubmit">
                <van-field
                v-model="username"
                name="username"
                label="用户名"
                placeholder="用户名"
                :rules="[{ required:true, message: '请填写用户名' }]"
                />
                <van-field
                v-model="password"
                type="password"
                name="password"
                label="密码"
                palceholder="密码"
                :rules="[{ required:true, message: '请填写密码' }]"
                />
                <!-- 验证码部分 -->
                <van-field
                    center
                    clearable
                    label="验证码"
                    placeholder="输入验证码"
                    v-model="verify"
                >
                    <template #button>
                        <vue-image-verify ref="verifyRef" />
                    </template>
                </van-field>
                <div style="margin: 16px;">
                    <div class="link-register" @click="toggle('register')">立即注册</div>
                    <van-button round block color="#1baeae" native-type="submit">登录</van-button>
                </div>
            </van-form>
        </div>
        <!-- -->
        <div v-else class="login-body register">
            <van-form @submit="onSubmit">
                <van-field
                v-model="loginName"
                name="loginName"
                label="登录名"
                placeholder="登录名"
                :rules="[{ required: true, message: '填写登录名' }]"
                />
                <van-field
                v-model="nickName"
                name="nickName"
                label="昵称"
                placeholder="昵称"
                :rules="[{ required: false, message: '填写昵称' }]"
                />
                <van-field
                v-model="password1"
                type="password"
                name="password1"
                label="密码"
                placeholder="密码"
                :rules="[{ required: true, message: '填写密码' }]"
                />
                <van-field
                v-model="password2"
                type="password"
                name="password2"
                label="确认密码"
                placeholder="确认密码"
                :rules="[{ required: true, message: '确认密码' }]"
                />
                <!-- 验证码部分 -->
                <van-field
                    center
                    clearable
                    label="验证码"
                    placeholder="输入验证码"
                    v-model="verify"
                >
                    <template #button>
                        <vue-image-verify ref="verifyRef" />
                    </template>
                </van-field>
                <div style="margin: 16px;">
                    <div class="link-login" @click="toggle('login')">已有登录账号</div>
                    <van-button round block color="#1baeae" native-type="submit">注册</van-button>
                </div>
            </van-form>
        </div>
    </div>
</template>

<script>
import { reactive, toRefs, ref, onMounted } from 'vue'
import CustomHeader from '../components/CustomHeader.vue'
import VueImageVerify from '@/components/VueImageVerify.vue'
import { login, register } from '../service/user'
import { getLocal, setLocal } from '../utils/help'
import { showSuccessToast, showFailToast, showToast } from 'vant'
export default {
    components: {
        CustomHeader,
        VueImageVerify
    },
    setup() {
        const verifyRef = ref(null)
        const state = reactive({
            username:'',
            password:'',
            loginName:'',
            nickName:'',
            password1:'',
            password2:'',
            type: 'login',
            verify: '',
            imgCode: ''
        })

        onMounted(() => {
            console.log('verifyRef.value', verifyRef.value)
        })

        const toggle = (v) => {
            state.type = v
        }


        const onSubmit = async (values) => {
            state.imgCode = verifyRef.value.imgCode || ''
            if (state.verify.toLowerCase() != state.imgCode.toLocaleLowerCase()) {
                showFailToast('验证码有误')
                return
            }
            if (state.type == 'login') {
                try {
                    // alert(getLocal('token'))
                    setLocal('token','')
                    const { resultCode, message, data } = await login({
                        "loginName": values.username,
                        "password": values.password
                    })
                    // alert(resultCode+' '+message+' '+data.userId)
                    if (data == '') {
                        showFailToast()
                    } else {
                        // alert(data.userId+"\n"+data.token+"\n"+data.insertTime)
                        // setLocal('userId', data.userId)
                        setLocal('token', data.token)
                        showToast('！！！！！！！！！！！！！！！该跳转了！！！！！！！！！！！！！')
                        window.location.href = '/'
                    }
                } catch(err) {
                    showToast('？？？？？？？？？？？？？？？？？？？？！！！！！！！！！！！！！')
                    showFailToast(err)
                }
                
            } else {
                if (values.password1 !== values.password2) {
                    showFailToast('两次密码不一致')
                    return
                } else {
                    try {
                        await register({
                            "loginName": values.loginName,
                            "nickName": values.nickName != "" ? values.nickName : values.loginName,
                            "password": values.password1
                        })
                        showSuccessToast('注册成功')
                    } catch(err) {
                        showFailToast(err)
                    }
                }
                
                state.type = 'login'
            }
        }
        return {
            ...toRefs(state),
            toggle,
            onSubmit,
            verifyRef
        }
    }
}
</script>

<style lang="less">
    .login {
        .logo {
            width: 270px;
            height: 233px;
            display: block;
            margin:5px auto
        }
        .login-body {
            padding: 0 20px;
        }
        .login {
            .link-register {
                font-size:14px;
                margin-bottom: 10px;
                color: #1989fa;
                display: inline-block;
            }
        }
        .register {
            .link-login {
                font-size: 14px;
                margin-bottom: 10px;
                color: #1989fa;
                display: inline-block;
            }
        }
    }
</style>
