Ext.define('app.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    requires: [
        'Ext.window.MessageBox'
    ],
    
    alias: 'controller.main',
    
    onSysmenuClick : function(btn,e){
    	var me = this;
    	var btns = btn.ownerCt.items.items;
    	var refresh = true;
    	Ext.Array.each(btns, function(menu) { 
    		if(menu.type && menu.type=='menu'){
    			if(menu.pressed){
    				if(menu.clazz===btn.clazz){
    					refresh = false;
    				}else{
    					menu.setPressed(false);
    				}
    			}
    		}
    	});
    	if(refresh){
    		btn.removeCls("x-focus");
    		btn.removeCls("x-btn-focus");
    		btn.setPressed(true);
    		btn.addCls("menu-active");
    		var view = this.getView();
    		var container = Ext.getCmp('main-content');
    		container.removeAll(true);
    		var app = Ext.create(btn.clazz);
    		container.add(app);
    	}
    },
    loadHome : function(){
//    	var home = Ext.getCmp('_Home');
    	var home = Ext.getCmp('_Ali');
    	home.fireEvent('click',home);
    }
});
