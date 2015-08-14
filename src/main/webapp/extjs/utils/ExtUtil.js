var ExtUtil = {
	slideActive : function(layout,activeItem,direct){
		layout.setActiveItem(activeItem);
		activeItem.el.slideIn(direct, {// 将元素从视图滑出并伴随着渐隐
			easing : 'easeOut',
			duration : 500,
			remove : false,
			useDisplay : false
		});
	},
	filterStore : function(oriStore,gridStore,filterString,ignores){
		if(!filterString){
			gridStore.removeAll();
			gridStore.add(oriStore.data.items);
			return;
		}
		var store = oriStore;
		var count = store.getCount();
		var orifields = store.getAt(0).getFields();
		var fields = [];
		if(ignores){
//			Ext.Array.each(orifields,function(f){
//				var isIgore = false;
//				Ext.Array.each(ignores,function(ig){
//					if(ig===f.name){
//						isIgore = true;
//					}
//				});
//				if(!isIgore){
//					fields.push(f.name);
//				}
//			});
			for(var i=0; i<orifields.length;i++){
				var isIgore = false;
				var fname = orifields[i].name;
				for(var j=0;j<ignores.length;j++){
					if(ignores[j]===fname){
						isIgore = true;
						break;
					}
				}
				if(!isIgore){
					fields.push(fname);
				}
			}
		}else{
			Ext.Array.each(orifields,function(f){
				fields.push(f.name);
			});
		}
		
		var matchs = [];
		for(var i=0; i<count; i++){
			var record = store.getAt(i);
			for(var j=0;j<fields.length;j++){
				var field = fields[j];
				if(record.get(field)==filterString){
					matchs.push(record);
					break;
				}
			}
		}
		gridStore.removeAll();
		gridStore.add(matchs);
	}
}