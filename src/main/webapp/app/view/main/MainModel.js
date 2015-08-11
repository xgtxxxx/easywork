/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('app.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.main',

    data: {
    	name : 'app',  
    	  
        // 系统信息  
        system : {  
            name : 'EasyWork System',  
            version : '5.2014.06.60',  
            iconUrl : 'styles/icons/sys.png'  
        }
    },
    
    getSysmenus : function(){
    	var me = this;
    	var menus = [];
    	Ext.Ajax.request({
    		async : false,
			url : CTX.PATH + '/app/mock/sysmunu.json',
			success : function(response) {
				var res = Ext.decode(response.responseText);
				if(res){
				  menus = res.sysmenu;
				}
			}
		});
    	return menus;
    },
    createMenuItem : function(text,clazz,iconCls,handler){
    	if(!handler){
    		handler = 'onSysmenuClick';
    	}
    	return {
				text 	  : text,
				iconCls   : iconCls,
				clazz     : clazz,
				cls       : 'menu-item',
				width	  : 100,
				height	  : 40,
				type      : "menu",
				listeners : {
					click : handler
				}
		};
    }
});