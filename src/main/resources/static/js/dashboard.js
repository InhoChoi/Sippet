function getPathName(data) {
    return data.pathName;
}

function getCount(data) {
    return data.count;
}

function createChartData(data, backgroundColor) {
    var chartLabels = _.map(data, getPathName);
    var chartDatas = _.map(data, getCount);

    return {
        labels: chartLabels,
        datasets: [{
            backgroundColor: backgroundColor,
            data: chartDatas
        }]
    };
}

Vue.component('visit-count-chart', {
    extends: VueChartJs.HorizontalBar,
    mixins: [VueChartJs.mixins.reactiveProp],
    mounted() {
        this.renderChart(this.chartData, {
           legend: {
                display: false
            },
            responsive: true,
            maintainAspectRatio: false,
            title: {
                display: true,
                text: '페이지별 클릭횟수'
            }
        })
    }

})

var app = new Vue({
    el: '#app',
    data: {
        charData: {},
    },
    mounted: function() {
        var _this = this;
        $.ajax({
            url: "/api/track/group_by_count/path_name",
            success: function(data){
                _this.charData = createChartData(data, '#f87979');
            }
        });
    }
})