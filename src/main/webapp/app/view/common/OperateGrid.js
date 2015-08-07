Ext.define('app.view.common.OperateGrid', {
	extend : 'Ext.grid.Panel',
	forceFit : true,
	stripeRows : true,// 斑马线效果
	loadMask : true,
	listeners : {
		cellclick : function( table, td, cellIndex, record, tr, rowIndex, e, eOpts){
			var a = e.target;
			if(a.tagName=='A' || a.tagName=='a'){
				var method = a.attributes['method'].value;
                this[method].call(this,record);
			}
		}
	},
	reload : function(){
		this.store.reload();
	},
	doRequest : function(params, url) {
		var grid = this;
		Ext.Ajax.request({
			url : CTX.PATH + url,
			params : params,
			success : function(response) {
				var res = Ext.decode(response.responseText);
				Ext.Msg.show({
					title : 'Reminder',
					message : res.message,
					buttons : Ext.Msg.OK,
					icon : res.success ? Ext.Msg.INFO : Ext.Msg.ERROR,
					fn : function(btn) {
						grid.getStore().reload();
					}
				});
			}
		});
	},
	confirm : function(message,callback){
		Ext.Msg.confirm("Confirm", message, function(v) {
			if (v == 'yes') {
				callback();
			}
		});
	}
});