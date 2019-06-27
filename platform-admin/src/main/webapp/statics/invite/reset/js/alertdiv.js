var err = {
    init: function() {
        //页面加载弹出层
        $('body').append('<div class="alertInit_N">' +
            '<div class="tkBox">' +
            '<div class="tkTop"></div>' +
            '<div class="tkBott">' +
            '<div class="midclose" onclick="err.closediv()">关闭</div>' +
            '</div> </div> </div>');

    },
    errordiv: function(error) {
        $('.alertInit_N').css('display', 'block');
        $('.tkTop').html(error); 
    },
    closediv: function() {
        $('.alertInit_N').css('display', 'none');
    },
    loading: function() {
        $('body').append('<div class="alertInit_L"><div class="spinner">' +
            '<div class="rect1"></div>' +
            '<div class="rect2"></div>' +
            '<div class="rect3"></div>' +
            '<div class="rect4"></div>' +
            '<div class="rect5"></div>' +
            '</div></div>');
    },
    closeloading: function() {
        $('.alertInit_L').remove();
    }
}
$(function() {
    err.init();
});
