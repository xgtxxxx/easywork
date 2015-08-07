var ExtUtil = {
	slideActive : function(layout,activeItem,direct){
		layout.setActiveItem(activeItem);
		activeItem.el.slideIn(direct, {// 将元素从视图滑出并伴随着渐隐
			easing : 'easeOut',
			duration : 500,
			remove : false,
			useDisplay : false
		});
	}
}