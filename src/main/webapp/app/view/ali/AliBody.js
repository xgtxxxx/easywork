Ext.define('app.view.ali.AliBody', {
	extend: 'Ext.panel.Panel',
	requires : ['app.view.ali.Ath4Report'],
    alias : 'widget.alibody',
    controller: 'ali',
    layout: 'fit',
    border : true,
    title : 'AHT4',
    initComponent : function() {
    	var me = this;
    	var searchBtn = Ext.create('Ext.button.Button',{
    		iconCls : 'icon-search',
    		listeners : {
    			click : 'searchGridContext'
    		}
    	});
    	var filter = Ext.create('Ext.form.field.Text',{
    		cls   : 'grid-search-box',
			fieldLabel : '全文搜索',
			labelWidth : 60,
			labelAlign : 'right',
			width : 500,
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                    	searchBtn.fireEvent('click',searchBtn);
                    }
                }
			}
    	});
    	this.getFilter=function(){
    		return filter.getValue();
    	},
    	this.tbar = Ext.create('Ext.toolbar.Toolbar',{
    		items : [filter,searchBtn]
    	});
    	this.items = Ext.create('app.view.ali.Ath4Report',{
    		border : false
    	});
    	this.callParent(arguments);
    	Ext.QuickTips.init();
    }
});