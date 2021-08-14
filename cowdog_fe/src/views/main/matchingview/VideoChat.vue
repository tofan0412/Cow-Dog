<template>
	<div id="" class="main-container">
		<!-- <div id="join" v-if="!session">
			<div id="img-div"><img src="resources/images/openvidu_grey_bg_transp_cropped.png" /></div>
			<div id="join-dialog" class="jumbotron vertical-center">
				<h1>Join a video session</h1>
				<div class="form-group">
					<p>
						<label>Participant</label>
						<input v-model="myUserName" class="form-control" type="text" required>
					</p>
					<p>
						<label>Session</label>
						<input v-model="mySessionId" class="form-control" type="text" required>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
					</p>
				</div>
			</div>
		</div> -->

		<div v-if="session && invited">
			<div class="result-card">
				<div class="result-card-img">
					<img :src=file_path class="profiile_image"> 
				</div>
				<div class="result-card-main-info">
					<div> {{memberid}} {{memberinfo.age}}세</div>  
				</div>
				<div class="result-card-body">
					<div class="body-content">주량: {{memberinfo.alcohol.replace('[','').replace(']','')}}</div>
					<div class="body-content">종교: {{memberinfo.religion.replace('[','').replace(']','')}}</div>
					<div class="body-content">성격: {{memberinfo.personality.replace('[','').replace(']','')}}</div>
					<div class="body-content">취미: {{memberinfo.hobby.replace('[','').replace(']','')}}</div>
				</div>
			<button @click="agreeMeeting">수락하기</button>
			<button @click="refuseMeeting">거절하기</button>
			</div> 
		</div>

		<div id="session" v-if="session && !invited" class="video-container" v-bind:class="{chatCreate:!chatStatus&&!gameStatus, cmCreate:startStatus2}">
			<div class="header1">
				<div class="header2">화상 채팅</div>
			</div>
			<!-- <div id="session-header">
				<input class="btn btn-large btn-primary" type="button" @click="refreshSession" value="refresh">
				<input class="btn btn-large btn-primary" type="button" @click="refuseMeeting" value="refresh2">
			</div> -->
			<!-- <div id="session-header">
				<h1 id="session-title">{{ mySessionId }}</h1>
				<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
			</div> -->
			<div class="col-md-6 video">
				<user-video :stream-manager="mainStreamManager"/>
			</div>
			<div class="col-md-6 video" v-if="matchAccept">
				<!-- <user-video :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/> -->
				<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
			</div>
						
			<!-- <button @click="micControll">test</button> -->
			<div class="nav">
			</div>
			<div class="nav1">
				<button class="btncss" @click="videoControll">
				얼굴변조
				</button>
				<button class="btncss" @click="voiceControll">
				<Icon v-if="voiceStatus" icon="ic:baseline-record-voice-over" color="white" width="30" height="30"/>
				<Icon v-else icon="ic:round-voice-over-off" color="white" width="30" height="30"/>
				<label class="la-cs">목소리변조</label>
				</button>
				<button class="btncss" @click="micControll">
					<Icon v-if="micStatus" icon="bi:mic-fill" color="white" width="30" height="30"/>
					<Icon v-else icon="bi:mic-mute-fill" color="white" width="30" height="30"/>
					<label v-if="micStatus" class="la-cs">음소거</label>
					<label v-else class="la-cs">음소거 해제</label>
				</button>
				<button class="btncss" @click="vidControll">
					<Icon v-if="vidStatus" icon="bi:camera-video-fill" color="white" width="30" height="30"/>
					<Icon v-else icon="bi:camera-video-off-fill" color="white" width="30" height="30"/>
					<label v-if="vidStatus" class="la-cs">카메라 끄기</label>
					<label v-else class="la-cs">카메라 켜기</label>
				</button>
				<button class="btncss" @click="chatControll">
					<Icon icon="bi:chat-dots" color="white" width="30" height="30" />
					<label class="la-cs">채팅</label>
				</button>
				<button class="btncss" @click="gameControll">
					<Icon icon="ion:game-controller-outline" color="white" width="30" height="30" />
					<label class="la-cs">게임</label>
				</button>
				<button class="btncss" @click="leaveSession">
					<Icon icon="ant-design:close-circle-filled" color="red" width="30" height="30" />
					<label class="la-cs">나가기</label>
				</button>
			</div>
		</div>

		<div id="chatbox" class="chat-container" v-if="chatStatus && session && !invited">
				<div class="chat-detail">
				<div class="chatheader">
					<span>채팅</span>
					<!-- <span id="exit-chat">X</span> -->
					</div>
				<div ref="chatmain" class="chatmain">
					<div v-for="(chat,index) in chatdata" :key="index">
						
						<div class="my-chat-box" v-if="myUserName == chat.userId">
							<div class="chat-block">
							<span class="my-chat-time">{{chat.now}}</span>
								<div class="my-chat">
									<span>{{chat.msg}}</span>
								</div>
							</div>
							<!-- <div class="my-chat-time">
								<span>{{chat.now}}</span><br>
							</div> -->
						</div>
						<div class="opp-chat-box" v-else>
							<div class="opp-block">
								<!-- <img class="user-profile" src="./assets/img/micoff.png" alt=""> -->
								<span class="opp-name">{{chat.userId}}</span>
							</div>
							<div class="opp-chat">
								<span>{{chat.msg}}</span>
							</div>
							<span class="opp-chat-time">{{chat.now}}</span>
						</div>
					</div>
				</div>
				
				<div class="send-box input-group">
					<input class="chat-bar form-control" v-model="msg" type="text" placeholder="메세지를 입력해주세요" @keydown.enter="sendMsg">
					<span class="input-group-btn">
					<button class="send-btn btn btn-default" @click="sendMsg"><Icon icon="cil:send" color="white" width="20" heigth="20"/></button>
					</span>
				</div>
			</div>
		</div>

		<div class="game-container" v-if="gameStatus && session && !invited">
			<div class="" v-if="!startStatus && !startStatus2" @click="startGame">
				<button v-if="!isReadyBal" class="start-btn">밸런스 게임</button>
				<button v-else class="start-btn">준비완료</button>
			</div>
			<div class="" v-if="!startStatus2 && !startStatus" @click="startGame2">
				<button v-if="!isReadyCat" class="start-btn">캐치마인드</button>
				<button v-else class="start-btn">준비완료</button>
			</div>
			<div class="" v-if="startStatus">
				<div class="select-box" @click="selectA">
					{{A_item}}
				</div>
				<div class="select-box" @click="selectB">
					{{B_item}}
				</div>

				<div class="select-result">
					<div v-for="(game,index) in gamedata" :key="index">
						<div>
							<span>{{game.userId}}님이 {{game.select}}를 선택하셨습니다.</span>
						</div>
					</div>
				</div>
				
				<button @click="nextProblem">다음문제</button>
			</div>
		</div>
		<div v-if="startStatus2 && myTurn">제시어:{{problem}}</div>
		<div class="draw-container" v-bind:class="{catDis:!startStatus2}">	
    <canvas
      id="myCanvas"
      width="560"
      height="360"
      @mousedown="beginDrawing"
      @mousemove="keepDrawing"
      @mouseup="stopDrawing"
    />
    <div class="draw-tool">
		<table class="draw-opt">
			<tr>
				<td><Icon class="colorBtn" icon="akar-icons:circle-fill" color="black" @click="colorChange('black')" width="24" height="24"/></td>
				<td><Icon class="colorBtn" icon="akar-icons:circle-fill" color="red" @click="colorChange('red')" width="24" height="24"/></td>
				<td><Icon class="colorBtn" icon="akar-icons:circle-fill" color="blue" @click="colorChange('blue')" width="24" height="24"/></td>
				<td><Icon class="colorBtn" icon="akar-icons:circle-fill" color="yellow" @click="colorChange('yellow')" width="24" height="24"/></td>
				<td><Icon class="colorBtn" icon="akar-icons:circle-fill" color="green" @click="colorChange('green')" width="24" height="24"/></td>
				<td><Icon class="colorBtn" icon="bi:eraser" @click="colorChange('white')" width="24" height="24"/></td>
				<td><button class="removeBtn" @click="allDelete">전체 지우기</button></td>
		</tr>
		</table>
    </div>
	<button @click="startCM" v-if="!catchMindStatus">시작</button>
	<div >남은 시간:{{count}}</div>
	</div>
	</div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/views/main/components/UserVideo';
import router from '../../../router';
import { Icon } from '@iconify/vue';
import { mapGetters } from 'vuex';
axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://i5b104.p.ssafy.io:9090";
const OPENVIDU_SERVER_SECRET = "cowdog123";

//const store = useStore();
const instance = axios.create({
	baseURL: OPENVIDU_SERVER_URL,
	headers: {'Content-Type': 'application/json'},
})
export default {
	name: 'VideoChat',

	components: {
		UserVideo,
		Icon,
	},
	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			mySessionId: '',
			myUserName: '',
			micStatus:false,
			vidStatus:false,
			chatStatus:true,
			gameStatus:false,
			chatdata: [],
			msg:'',
			A_item_list: ["토맛 토마토","평생 샤워안하기","카레맛 똥", "팔만대장경 다 읽기","겨울 에어컨" ,"월 200 백수", "볼빨간 사춘기"],
			B_item_list: ["토마토맛 토","평생 양치안하기","똥맛 카레", "대장내시경 팔만번 하기","여름 히터", "월 500 직장인", "사촌간 볼빨기"],
			A_item:'',
			B_item:'',
			startStatus:false,
			gIndex:0,
			gamedata:[],
			isSelected:false,
			gameStart:[],
			gameStart2:[],
			isReadyBal:false,
			nextStatus:false,
			voiceStatus:false,
			matchAccept:false,
			invited:false,
			user:undefined,
			file_path:'',
			genderIcon:'',
			memberinfo:'',
			memberid:'',
			dist:'',
			x: 0,
			y: 0,
			isDrawing: false,
			canvas: null,
			color: "black",
			penSize:2,
			cmStatus:false,
			isReadyCat:false,
			catchProblem:["샴푸","송아지","강아지","소방관","포크레인"],
			problem:'',
			catchMindStatus:false,
			myTurn:false,
			count:60,
			catchIndex:0,
		}
	},
	computed: {
    ...mapGetters(['getUserInfo','getUserToken']),
	},
	created(){
		this.mySessionId = this.$route.query.opp;
		this.myUserName = this.getUserInfo.memberid;
		//this.file_path = this.$route.params.path;
		//onsole.log(this.user);
		console.log(this.myUserName+"내 이름 이다");
        //console.log(this.$route.query.matchType+"tesat");					
		
		this.getData(this.mySessionId);
		console.log(this.memberinfo);
		//this.refuseMeeting();
	},
	mounted() {
    var c = document.getElementById("myCanvas");
	this.canvas = c.getContext(`2d`);
	},
	methods: {
		refreshSession(){
			console.log(this.matchAccept);
			// window.open('https://i5b104.p.ssafy.io:9090/#/a');
			// this.leaveSession();
			// setTimeout(() => {
			// //this.joinSession();
			// }, 1000);
		},
		videoControll(){
			this.publisher.stream.applyFilter("FaceOverlayFilter")
    .then(filter => {
        filter.execMethod(
            "setOverlayedImage",
            {
                "uri":"https://cdn.pixabay.com/photo/2013/07/12/14/14/derby-148046_960_720.png",
                "offsetXPercent":"0.7F",
                "offsetYPercent":"-0.8F",
                "widthPercent":"2.0F",
                "heightPercent":"2.0F"
            });
    });
		},
		agreeMeeting(){
			this.matchAccept=true;
			this.invited=false;
			this.session.signal({
				data: 'agree',
				to:[],
				type: 'match'
			})
		},
		refuseMeeting(){
			this.matchAccept=false;
			this.session.signal({
				data: 'refuse',
				to:[],
				type: 'match'
			})
			setTimeout(() => {
				this.leaveSession();
				router.push({name:'Main'});
			}, 2000);
		},
		voiceControll(){
			this.getData(this.mySessionId);
			this.voiceStatus=!this.voiceStatus;
			let pitchs = ['0.76', '0.77', '0.78', '0.79', '0.80', '1.3', '1.4', '1.5', '1.6', '1.7']
            let pitch = pitchs[Math.floor(Math.random() * pitchs.length)]
			if(this.voiceStatus){
				this.publisher.stream.applyFilter("GStreamerFilter", {"command": `pitch pitch=${pitch}`});
			}
			else{
				this.publisher.stream.removeFilter();
			}
		},
		chatscroll(){
			this.$refs.chatmain.scrollTop = this.$refs.chatmain.scrollHeight;
		},
		sendMsg(){
			this.msg = this.msg.trim();
			let date = new Date();
			let now = '';
			let min = date.getMinutes();
				if(min < 10){
					min = "0"+date.getMinutes();
				}
			if(date.getHours()>12){
				now ="오후 "+ (date.getHours()-12) +":"+min+"";
			}
			else{
				now ="오전 "+ date.getHours()+":"+min+"";
			}
			if(this.msg==''){
				return;
			}
			const sdata = {
				userId : this.myUserName,
				msg : this.msg,
				now : now
			};
			
			this.session.signal({
				data: JSON.stringify(sdata),
				to:[],
				type: 'my-chat'
			})
			.then(() => {
				this.msg='';
				console.log('Message successfully sent');
			})
			.catch(error => {
				console.log(error);
			})
		},
		chatControll(){
			this.chatStatus = !this.chatStatus;
			this.gameStatus = false;
			if(this.chatStatus){
				setTimeout(this.chatscroll,10);
			}
		},
		micControll () {
			this.micStatus = !this.micStatus;
			this.publisher.publishAudio(this.micStatus);	
		},
		vidControll(){
			this.vidStatus = !this.vidStatus;
			this.publisher.publishVideo(this.vidStatus);
		},
		gameControll(){
			if(this.startStatus2){
				this.startStatus2 = !this.startStatus2;
			}
			this.gameStatus = !this.gameStatus;
			this.chatStatus = false;
		},
		startGame(){
			this.isReadyBal=!this.isReadyBal;
			this.session.signal({
				data: this.isReadyBal,
				to:[],
				type: 'game-start'
			})
		},
		startGame2(){
			this.isReadyCat=!this.isReadyCat;
			this.session.signal({
				data: this.isReadyCat,
				to:[],
				type: 'game-start2'
			})
		},
		nextProblem(){
			if(!this.nextStatus && this.isSelected){ 
				alert("상대방이 선택하지 않았습니다.");
				return;
			}
			else if(!this.nextStatus && !this.isSelected){
				alert("둘중 하나를 선택하세용")
			}
			this.nextStatus=false;
			this.isSelected=false;

			this.session.signal({
				data: "next",
				to:[],
				type: 'game-next'
			})
			
		},
		selectA(){
			if(this.isSelected) return;
			this.isSelected = true;
			const sdata = {
				userId : this.myUserName,
				select : this.A_item,
			};
			
			this.session.signal({
				data: JSON.stringify(sdata),
				to:[],
				type: 'my-game'
			})
			.then(() => {
				this.msg='';
				console.log('Message successfully sent');
			})
			.catch(error => {
				console.log(error);
			})
		},
		selectB(){
			if(this.isSelected) return;
			this.isSelected = true;
			const sdata = {
				userId : this.myUserName,
				select : this.B_item,
			};
			
			this.session.signal({
				data: JSON.stringify(sdata),
				to:[],
				type: 'my-game'
			})
			.then(() => {
				this.msg='';
				console.log('Message successfully sent');
			})
			.catch(error => {
				console.log(error);
			})
		},
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();

			// --- Specify the actions when events take place in the session ---

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

			this.session.on('signal:my-chat', (event) => {
				this.chatdata.push(JSON.parse(event.data));
				setTimeout(this.chatscroll,10);
			});

			this.session.on('signal:my-game', (event) => {
				this.gamedata.push(JSON.parse(event.data));
				if(this.gamedata.length==2){
					this.nextStatus=true;
				}
			});

			this.session.on('signal:game-start',(event) => {
				if(event.data){
					this.gameStart.push(event.data);
				}
				else{
					this.gameStart.pop();
				}
				if(this.gameStart.length==2){
					this.startStatus=true;
					this.A_item = this.A_item_list[this.gIndex];
					this.B_item = this.B_item_list[this.gIndex];
				}
			});

			this.session.on('signal:game-start2',(event) => {
				if(event.data){
					this.gameStart2.push(event.data);
				}
				else{
					this.gameStart2.pop();
				}
				if(this.gameStart2.length==2){
					this.startStatus2=true;
					this.gameStatus=false;
					this.chatStatus=false;
				}
			});
			this.session.on('signal:game-next',(event) =>{ 
				if(this.gamedata.length==2){
					if(this.gIndex<this.A_item_list.length-1){
						this.nextStatus=false;
						this.isSelected=false;
						this.gIndex++;
						this.A_item = this.A_item_list[this.gIndex];
						this.B_item = this.B_item_list[this.gIndex];
						this.gamedata=[];
					}
					else{
						alert("게임 끝~");
						this.gameStart=[];
						this.gamedata=[];
						this.startStatus = false;
						this.isReadyBal = false;
						this.gIndex=0;
					}
					console.log(event);
				}
			});
			this.session.on('signal:match',(event)=>{
				//this.AcceptInfo.push("reject");
				
				if(!this.invited && event.data=="refuse"){
					alert("상대방이 거절하였습니다.");
					setTimeout(() => {
						this.leaveSession();
					}, 2000);
					router.push({name:'Main'});
				}
				else if(!this.invited && event.data=="agree" &&(this.mySessionId != this.myUserName)){
					alert("상대방이 수락하였습니다.")
					this.matchAccept=true;
				}
				
				console.log(event.data);
			});
			this.session.on('signal:catch-start',(event)=>{
				this.catchMindStatus=true;
				console.log(event);
				this.problem = this.catchProblem[this.catchIndex++];
				this.myTurn = !this.myTurn;
			})
			this.session.on('signal:start-draw',(event)=>{
				let sm = JSON.parse(event.data);
				this.x = sm.x;
				this.y = sm.y;
				
			})
			this.session.on('signal:drawing',(event)=>{
				let sm = JSON.parse(event.data);
				
				this.drawLine(this.x, this.y, sm.x, sm.y, this.color);
				this.x = sm.x;
				this.y = sm.y;
			})
			
			this.session.on('signal:drawing-opt',(event)=>{
				
				if(event.data=="all"){
					this.canvas.clearRect(0, 0, 560, 360);
				}
				else if(event.data=="white"){
					this.penSize = 4;
					this.color = event.data;
				}
				else{
					this.color = event.data;
					this.penSize = 2;
				}
				
			})

			
			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			axios.get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,'',{
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},}).then(response=>{
			console.log("테스트입니다.")
			console.log(response.data);
		})

			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						// --- Get your own camera stream with the desired properties ---
						console.log("얼마나 나오나 보자");
						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: false,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: false,  	// Whether you want to start publishing with your video enabled or not
							resolution: '400x360',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: true       	// Whether to mirror your local video or not
						});

						this.mainStreamManager = publisher;
						this.publisher = publisher;

						// --- Publish your stream ---

						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			this.invited=false;
			console.log(this.matchAccept);
			
			if (this.session) this.session.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);

			router.push({name:"Main"});
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				instance
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				instance
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, JSON.stringify({
            "session": sessionId,
            "kurentoOptions": {
              "allowedFilters": ["GStreamerFilter", "FaceOverlayFilter"]
            }
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},

		getData (sessionId) {
			return new Promise((resolve, reject) => {
				instance
					.get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => {
						let content = response.data.content;
						console.log("여기보세요");
						
						for(let i=0;i<content.length;i++){
							if(content[i].id==this.myUserName){
								console.log(content[i].connections.content[0].clientData);
								let userid = content[i].connections.content[0].clientData.replace(/"/g,"").replace(":","").replace("clientData","").replace("{","").replace("}","");
								console.log(userid);
								axios.get("http://localhost:8080/cowdog/mem/getOppInfo/?userId="+userid,{
									headers:{
										Authorization:"Bearer "+ this.getUserToken
									}
								})
								.then(res=>{
									console.log(res.data);
									this.file_path=res.data.file_path;
									this.memberinfo = res.data.memberinfo;
									this.memberid = res.data.memberid;
									this.dist = res.data.dist;
								})
								
								this.mySessionId = this.myUserName;
								this.joinSession();
								this.invited=true;
								this.myTurn=true;
								console.log(response);
								return;
							
                            }
						}
						this.joinSession();
					})
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},
		drawLine(x1, y1, x2, y2, color) {
			let ctx = this.canvas;
			ctx.beginPath();
			ctx.strokeStyle = color;
			ctx.lineWidth = this.penSize;
			ctx.moveTo(x1, y1);
			ctx.lineTo(x2, y2);
			ctx.stroke();
			ctx.closePath();
		},
	beginDrawing(e) {
		if(!this.myTurn)return;
		this.x = e.offsetX;
		this.y = e.offsetY;
		this.isDrawing = true;

		const sdata = {
			x : e.offsetX,
			y : e.offsetY,
			isDrawing : true,
		}; 
		this.session.signal({
				data: JSON.stringify(sdata),
				to:[],
				type: 'start-draw'
		})
    },
    keepDrawing(e) {
      if (this.isDrawing === true) {
		const sdata = {
			x : e.offsetX,
			y : e.offsetY,
			isDrawing : true,
		}; 
		this.session.signal({
				data: JSON.stringify(sdata),
				to:[],
				type: 'drawing'
		})
      }
    },
    stopDrawing(e) {
      if (this.isDrawing === true) {
		const sdata = {
			x : e.offsetX,
			y : e.offsetY,
			isDrawing : false,
		};
		this.session.signal({
				data: JSON.stringify(sdata),
				to:[],
				type: 'drawing'
		})
        //this.x = 0;
        //this.y = 0;
        this.isDrawing = false;
      }
    },
	colorChange(col) {
		this.session.signal({
			data: col,
			to:[],
			type: 'drawing-opt'
		})
    },
    allDelete() {
		this.session.signal({
			data: "all",
			to:[],
			type: 'drawing-opt'
		})
    },
	startCM(){
		if(this.catchIndex>5){
			this.catchIndex=0;
			return;
		}
		console.log("디버그비거브");
		this.session.signal({
			data: '',
			to:[],
			type: 'catch-start'
		})
		
		setTimeout(() => {
				this.startCM();
		}, 10000);
		
	}
	}
}
</script>
<style scoped>
.main-container{
	width:100%;
}
.video-container{
	width:75%;
	float:left;
	padding:30px;
}
.game-container{
	float:right;
	width:22%;
	padding:2%;
	border:2px solid #FF427E;
	margin-top:5%;
	margin-right:2%;
}
.chat-container{
	float:right !important;
	width:25% !important;
	padding:2% !important;
} 
.chatCreate{
	width:90%;
	
}
/* #chatbox{
	position: absolute;
	z-index: 100;
	top:12%;
	left:80%;
	width: 300px;
	height:700px;
} */
.chatmain{
  position: relative !important;
  width: 100%;
  height: 500px;
  background-color:#ffe2ed;
  overflow: auto;
  padding: 10px;
}
/* .chat-bar{
	/* margin-left:5px;
	margin-top:5px;
	margin-bottom: 5px;
	width:80%;
	height:5%; */
.send-btn{
	/* padding-top:5px;
	width:18%;
	height:5%; */
	background-color: #FF427E;
	/* border-radius: 3px; */
	border-color: #FF427E;
}
.header1{
	background-color: #FF427E;
	color: white;
	text-align: center;
	width:100%;
	height:40px;
	vertical-align:middle;
	font-size: 20px;
	margin-bottom: 20px;
}
.header2{
	padding-top:5px;
}
.chatheader{
	background-color:#ffe2ed;
	text-align: center;
}
.nav1{
	background-color: #323545;
	text-align: center;
	vertical-align: middle;
	height: 10%;
}
.btncss{
	margin-top:1%;
	margin-right:15px;
	background-color: #323545;
	border: #323545;
	height:70%;
	width:9%;
}
.btncss:hover{
	transform: scale(1.1);
    cursor:pointer;
}
.blk{
	height:20px;
}
.la-cs{
	display: block;
	margin-top: 4px;
	width:100%;
	height:25%;
	color: #FFFFFF;
	
}
#exit-chat{
	float:right;
}
.chat-detail{
	width:80%;
	background-color: #F5F5F5;
}
.send-box{
	border-top: 1px solid white;
}
@media(max-height: 700px) and (max-width: 1400px){
		.la-cs{
			display: none;
		}
}
.select-box{
	border-color: #FF427E;
	border-radius: 2px;
	border:1px solid;
	color:#FF427E;
	margin: 3%;
	text-align: center;
	height:20%;
	margin-top:15%;
	padding-top: 15%;
	font-size:30px;

}
.select-box:hover{
	transform: scale(1.1);
    cursor:pointer;
}
.start-btn{
	border-color: #FF427E;
	border-radius: 2px;
	border:1px solid;
	color:#FF427E;
	margin: 3%;
	text-align: center;
	height: 50px;
	width:50px;
}
.opp-chat{
	border-radius: 2px;
	border:2px #FF427E;
	background-color: white;
}
.my-chat{
	border:2px white;
	background-color: #FF427E;
	color:white;
    box-shadow: -1px 1px 0 rgba(0,0,0,0.3);
	padding-right: 4px;
	padding-left:  4px;
	padding: 0.8rem;
    border-radius: 1rem;

	display: inline-block;
	
}
.my-chat-time{
	color: black;
	font-size: 5px;
	margin-right: 4px;
}
.my-chat-box{
	margin-bottom: 4px;
	overflow:hidden;
}
.test{
	overflow:hidden;
}
.chat-block{
	float:right;
}
.user-profile{
	width: 30px;
	height: 30px;
	border-radius: 3px;
	
}

.opp-chat{
	border-radius: 3px;
	border:2px black;
	background-color: white;
	color:black;
    box-shadow: 1px 1px 0 rgba(0,0,0,0.3);
	padding-right: 4px;
	padding-left:  4px;

	float:left;
    padding:0.8rem;
    border-radius:1rem;
	
}
.opp-chat-box{
	clear: both;
}
.opp-chat-time{
	color: black;
	font-size: 5px;
	margin-left: 4px;
	line-height: 5.5;
}
.opp-block{
	margin-bottom: 3px;
}
#myCanvas {
  border: 1px solid grey;
}
.draw-container{
	text-align: center;
}
.colorBtn{
	margin-right:4px;
    cursor:pointer;
	margin-top:4px;
}
.colorBtn:hover{
	transform: scale(1.1);
    cursor:pointer;
} 
.draw-tool{
	vertical-align: middle;
}
.removeBtn{
	color: #FF427E;
	background-color: white ;
	border : 1px solid #FF427E;
	border-radius: 4px;
}
.removeBtn:hover{
	transform: scale(1.1);
    cursor:pointer;
}
.draw-opt{
	margin: auto;
}
.cmCreate{
	width:50%;
}
.videoSet{
	max-width:60%;
	height:auto;
}
.catDis{
	visibility: hidden;
}
</style>
