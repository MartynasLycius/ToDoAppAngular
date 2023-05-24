//home.js
angular
.module('app');

//convert Hex to RGBA
function convertHex(hex,opacity){
  hex = hex.replace('#','');
  r = parseInt(hex.substring(0,2), 16);
  g = parseInt(hex.substring(2,4), 16);
  b = parseInt(hex.substring(4,6), 16);

  result = 'rgba('+r+','+g+','+b+','+opacity/100+')';
  return result;
}



dateRangeCtrl.$inject = ['$scope'];
function dateRangeCtrl($scope) {
  $scope.date = {
    startDate: moment().subtract(5, 'days'),
    endDate: moment()
  };
  $scope.opts = {
    drops: 'down',
    opens: 'left',
    ranges: {
      'Today': [moment(), moment()],
      'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
      'Last 7 days': [moment().subtract(7, 'days'), moment()],
      'Last 30 days': [moment().subtract(30, 'days'), moment()],
      'This month': [moment().startOf('month'), moment().endOf('month')]
    }
  };

  //Watch for date changes
  $scope.$watch('date', function(newDate) {
    //console.log('New date set: ', newDate);
  }, false);

  function gd(year, month, day) {
    return new Date(year, month - 1, day).getTime();
  }
}
