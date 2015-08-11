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
		},
		width : 40,
		height : 40,
		margin : '0 0 0 50'
	}, '->'],
	initComponent : function() {  
		var me = this;
		var vm = this.getViewModel();
		var ms = vm.getSysmenus();
		//Add home
		var homebtn = vm.createMenuItem("Home","app.view.portal.PortalView","");
		homebtn.id = "home-menu";
		this.items.push(homebtn);
		//Add other
		Ext.Array.each(ms, function(item) { 
			me.items.push(vm.createMenuItem(item.text,item.clazz,item.iconCls));
		});
        this.items.push('->');
        this.items.push({
        	xtype : 'button',
        	tooltip : 'Setting',
        	iconCls : 'glyphicon glyphicon-cog',
        	cls       : 'menu-item',
        	width : 40,
        	height: 40
        });
        this.callParent();  
    },
	listeners : {
    	afterrender : 'loadHome'
    }
});