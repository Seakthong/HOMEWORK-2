$('#btn-save-change').on('click',function(){
    console.log("work changed value")
    $('#btn-save-change').attr('href',"../article/returnPage?limited="+$('#in-limit').val());
})


$('.page-link').on('click', function(){
    console.log($(this).parent().parent())
})

$(document).ready(function () {
    let selectedPage = $('#currentPage').text();
    $('.pagination').children().removeClass('active');
    console.log(selectedPage)
    console.log($('.pagination').children().eq(parseInt(selectedPage)+1).addClass('active'))

})