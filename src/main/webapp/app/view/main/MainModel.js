/**
 * 不能被多个view引用，不然会创建多个model实例
 * 主要是因为目前没有找到设置viewmodel为单例的方法
 */
Ext.define('app.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.main',
    
    constructor : function() {  
        var me = this;  
        // 这一句是关键，如果没有的话，this还没有初始化完成,下面的Ext.apply(me.data,....)这句就会出错  
        this.callParent(arguments);  
        // 同步调用取得系统参数  
        Ext.Ajax.request({  
            url : CTX.PATH+'/initData.do',  
            async : false, // 同步  
            success : function(response) {  
                var text = response.responseText;  
                // 将字段串转换成本地变量  
                var applicationInfo = Ext.decode(text, true);  
                // 把从后台传过来的参数加入到data中去  
                Ext.apply(me.data, applicationInfo);  
            }  
        });  
    },  
    getSysmenus : function(){
    	return this.data.menu;
    },
    createMenuItem : function(pid,text,clazz,iconCls,handler){
    	if(!handler){
    		handler = 'onSysmenuClick';
    	}
    	return {
				text 	  : text,
				id        : "_"+text,
				pid       : pid,
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