<template>
    <div class="result-card">
        <div class="result-card-img">
            <img :src="this.user.file_path" class="profiile_image">
        </div>
        <div class="result-card-main-info">
            <div>{{this.user.memberid}}
                (<span v-if="genderIcon"><i class="fas fa-mars"></i></span>
                <span v-else><i class="fas fa-venus"></i></span>
                , {{this.user.memberinfo.age}}세)</div>
            <div class="like_btn" @click="like(this.user.memberid)"><i class="fas fa-heart"></i></div>
        </div>
        <div class="result-card-body">
            <div class="body-content">주량: {{this.user.memberinfo.alcohol.replace('[','').replace(']','')}}</div>
            <div class="body-content">종교: {{this.user.memberinfo.religion.replace('[','').replace(']','')}}</div>
            <div class="body-content">성격: {{this.user.memberinfo.personality.replace('[','').replace(']','')}}</div>
            <div class="body-content">취미: {{this.user.memberinfo.hobby.replace('[','').replace(']','')}}</div>
            <div class="body-content">사는곳: {{userRegion}}</div>
            <div class="body-content">MBTI: {{this.user.memberinfo.mymbti}}km 이내에 있습니다.</div>
        </div>
        <div class="card-footer">
            <div class="card-button">
                <div class="card-matcing-btn"><i class="fas fa-video"></i> 화상챗 요청</div>
            </div>
        </div>  
    </div>
</template>

<script>
import { reactive } from '@vue/reactivity'
import router from '../../../router'
import { useStore } from 'vuex'
    export default {  
        name: 'FollowDetail.vue',
        props: {
            user: Object,
        },
        computed: {
            userRegion() {
                var userRegionSplit = this.user.memberinfo.address.split(' ')
                var userRegion = userRegionSplit[0] + ' ' + userRegionSplit[1]
                return userRegion
            },
            genderIcon() {
                if(this.user.memberinfo.gender == "남자"){
                    return true
                } else {
                    return false
                }
            }
        },
        setup() {
            const store = useStore()
            const state = reactive({})
            const back=function(){
                router.push("/main")
            }
            const like=function(memberid){//팔로우~
                console.log("팔로우~~")
                console.log(memberid)
                store.dispatch("like",memberid)
            }
            return { state, back, like }
        },
    }
</script>

<style>
</style>