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
            label: 'Accumulate Visitor',
            backgroundColor: backgroundColor,
            data: chartDatas
        }]
    };
}

//function getDate(data) {
//    console.log(data.date);
//    return data.date + "." + data.pathName;
//}
//
//function getVisitCount(data) {
//    console.log(data.visitCount);
//    return data.visitCount
//}
//
//function createVisitCountChartData(data, backgroundColor) {
//    var chartLabels = _.map(data, getDate);
//    var chartDatas = _.map(data, getVisitCount);
//
//    return {
//        labels: chartLabels,
//        datasets: [
//        {
//            label: 'Visitor By Dates',
//            backgroundColor: backgroundColor,
//            data: chartDatas
//        }
//        ]
//    };
//}

function createVisitCountTables(data) {
    console.log("createVisitCountTables");
//    console.log(data);

    for(var i = 0; i < data.length; i++) {
        console.log(data[i]);

    }
}

Vue.component('accumulate-visit-count-chart', {
    extends: VueChartJs.HorizontalBar,
    mixins: [VueChartJs.mixins.reactiveProp],
    mounted() {
        this.renderChart(this.chartData, {
           categoryPercentage: 1.0,
           barPercentage: 1.0,
           legend: {
                display: false
            },
            responsive: true,
            maintainAspectRatio: false,
            title: {
                display: true
            }
        })
    }

})

Vue.component('visit-count-group-by-date-chart', {
    extends: VueChartJs.Bar,
    mixins: [VueChartJs.mixins.reactiveProp],
    mounted() {
        this.renderChart(this.charData, {
           categoryPercentage: 1.0,
           barPercentage: 1.0,
           legend: {
                display: false
            },
            responsive: true,
            maintainAspectRatio: false,
            title: {
                display: true
            }
        })
    }

})

var app = new Vue({
    el: '#app',
    data: {
        charData: {},
//        visitCountChartData: {},
        visitCountArray: [],
        totalVisitorCount: "1",
        newVisitorCount: "1"
    },
    beforeCreate: function() {
        var _this = this;

        $.ajax({
            url: "/api/track/group_by_count/path_name",
            success: function(data){
               _this.charData = createChartData(data, '#f87979');
            }
        });

        $.ajax({
            url: "/api/track/group_by_count/visit_count",
            success: function(data){
//                _this.visitCountChartData = createVisitCountChartData(data, '#f87979');
//                console.log(_this.visitCountChartData);
//                _this.visitCountArray = createVisitCountTables(data);
                _this.visitCountArray = data.slice();
                console.log(data);
                console.log(_this.visitCountArray);

                console.log(_this.visitCountArray[0].date);
                console.log(_this.visitCountArray[0].pathName);
                console.log(_this.visitCountArray[0].visitCount);

                console.log(_this.visitCountArray[1].date);
                console.log(_this.visitCountArray[1].pathName);
                console.log(_this.visitCountArray[1].visitCount);

                console.log(_this.visitCountArray[2].date);
                console.log(_this.visitCountArray[2].pathName);
                console.log(_this.visitCountArray[2].visitCount);
            }
        });

        $.ajax({
            url: "/api/track/count/visitor",
            success: function(data){
                _this.totalVisitorCount = data.total;
                _this.newVisitorCount = data.newVisitor;
            }
        });
    }
})