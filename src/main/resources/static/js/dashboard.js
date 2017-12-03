$.ajax("/api/track/group_by_count/path_name")
  .done(function(data) {
    var labels = _.map(data, function(data) {
      return data.pathName;
    });

    var countData = _.map(data, function(data) {
      return data.count;
    });

    var options = {
      type: 'horizontalBar',
      data: {
        labels: labels,
        datasets: [{
          label: "Count by path name",
          data: countData,
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    };

    new Chart($("#myChart"), options);
    new Chart($("#myChart2"), options);
    new Chart($("#myChart3"), options);
    $("#myChart").removeClass();
  });