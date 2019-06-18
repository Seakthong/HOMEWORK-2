$('#btn-save-change').on('click',function(){
    $('#btn-save-change').attr('href',"../article/returnPage?limited="+$('#in-limit').val());
})


$('.page-link').on('click', function(){
    console.log($(this).parent().parent())
})