/**
 * 系统主页的顶部区域，主要放置系统名称，菜单，和一些快捷按钮
 */
Ext.define('app.view.main.MainHeader', {

	extend : 'Ext.toolbar.Toolbar',
	
	controller : 'main',
	
	viewModel : 'main',

	alias : 'widget.mainheader', // 定义了这个组件的xtype类型为maintop

	items : [ {
		xtype : 'image',
		bind : { // 数据绑定到MainModel中data的ystem.iconUrl
			hidden : '{!system.iconUrl}', // 如果system.iconUrl未设置，则此image不显示
			src : '{system.iconUrl}' // 根据system.iconUrl的设置来加载图片
		}
	}, {
		xtype : 'label',
		bind : {
			text : '{system.name}' // text值绑定到system.name
		},
		style : 'font-size : 20px; color : blue;'
	}, {
		xtype : 'label',
		bind : {
			text : '{system.version}'
		}
	}, '->'],
	initComponent : function() {  
		var menus = this.getViewModel().getSysmenus();
        this.items = this.items.concat(menus);  
        this.items.push('->');
        this.items.push('->');
        this.items.push({text : 'Setting'});
        this.callParent();  
    }  
});