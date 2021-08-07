<template>
  <div id="map" style="width: 500px; height: 500px;"></div>
</template>

<script>
export default {
    props: {
        lat: String,
        lng: String,
        meters: Number,
    },
    setup(props) {
        const initMap = () => {
            var container = document.getElementById('map');
            var options = {
              center: new kakao.maps.LatLng(props.lat, props.lng),
              level: 8
            };

            const map = new kakao.maps.Map(container, options);

            var circle = new kakao.maps.Circle({
                center: new kakao.maps.LatLng(props.lat, props.lng),
                radius: props.meters * 1000,
                strokeWeight: 5, // 선의 두께
                strokeColor: '#ff4e7e', // 선의 색깔
                strokeOpacity: 1, // 불투명
                strokeStyle: 'line',
                fillColor: '#CFE7FF', // 채우기 색깔
                fillOpacity: 0.7 // 채우기 불투명도
            })
            circle.setMap(map);
            setTimeout(() => {
                map.relayout()
                map.setCenter(new kakao.maps.LatLng(props.lat, props.lng))
                console.log('done!!')
            }, 1000)
        }
        
        if (window.kakao && window.kakao.maps) {
            initMap();
        } else {
            const script = document.createElement('script');
            script.onload = () => kakao.maps.load(initMap);
            script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0f0de04704d7e38d69c53bd08ce9a2b8&libraries=services';
            document.head.appendChild(script);
        }
    },

}
</script>
<style>
</style>