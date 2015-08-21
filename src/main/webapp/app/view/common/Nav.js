Ext.define('app.view.common.Nav', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.nav',
	rootVisible : false,
	region: 'west',
    collapsible : true,
	collapsed : false,
	split : true,
    width : 200,
	initComponent : function() {
		var me = this;
		
		this.store.on("load", me._checkLeaf, me);
		
		this.callParent(arguments);
	},
	listeners : {
    	itemclick : 'addActivePanel'
    },
    _checkLeaf : function( s, records, successful, operation, node, eOpts){
    	var me = this;
    	if(successful){
    		Ext.each(records,function(record){
    			me._addInitPanel(record);
    		});
    	}
    },
    _addInitPanel : function(record){
    	var me = this;
    	if(record.data.leaf){
			if(record.data.isActive){
				me.fireEvent('itemclick',me,record)
			}
		}else{
			Ext.each(record.data.children,function(d){
				var r = {};
				r.data = d;
				me._addInitPanel(r);
			});
		}
    }
});
