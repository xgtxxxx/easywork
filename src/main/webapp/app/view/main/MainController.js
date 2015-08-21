Ext.define('app.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    requires: [
        'Ext.window.MessageBox'
    ],
    uses : ['app.view.common.BaseView'],
    
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
    		var app;
    		if(btn.id=='_Home'){
    			app = Ext.create(btn.clazz);
    		}else{
    			var store = me._createTreeStore(btn.pid);
    			app = Ext.create('app.view.common.BaseView',{
    				navStore : store
    			});
    		}
    		container.add(app);
    	}
    },
    loadHome : function(){
    	var home = Ext.getCmp('_Home');
    	home.fireEvent('click',home);
    },
    _createTreeStore : function(pid){
    	var store = Ext.create('Ext.data.TreeStore',{
			autoLoad : true,
			root : {
		    	expanded : true
		    },
		    proxy : {
	            type: 'ajax',
	            url:  CTX.PATH+'/sys/listSubMenu.do',
	            extraParams : {
	            	pid : pid
	            },
	            reader: {
	                type: 'json'
	            }
	        }
		});
    	return store;
    }
});
