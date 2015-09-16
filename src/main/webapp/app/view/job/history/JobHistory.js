Ext.define('app.view.job.history.JobHistory', {
	extend : 'app.view.common.OperateGrid',
	alias : 'widget.jobhistory',
	requires : ['app.view.job.history.JobHistoryController'],
	controller : 'jobhistory',
    initComponent : function() {
    	this.store = Ext.create('app.store.job.JobHistoryStore');
		this.columns = [ {
			xtype : 'rownumberer'
		}, {
			header : 'JobName',
			dataIndex : 'jobName'
		}, {
			header : 'TriggerName',
			dataIndex : 'triggerName'
		}, {
			header : 'RunTime',
			dataIndex : 'runTime',
			xtype : 'datecolumn',
			format : 'Y-m-d H:i:s'
		}, {
			header : 'Success',
			dataIndex : 'success',
			width : 50,
			renderer : function(v){
				var color = 'green';
				if(v==false){
					color = 'red';
				}
				return '<font color="'+color+'">'+v+'</font>';
			}
		}];
		this.dockedItems = [{
	        xtype: 'pagingtoolbar',
	        store: this.store, 
	        dock: 'bottom',
	        displayInfo: true
	    },{
	        xtype: 'toolbar',
	        dock: 'top',
	        items : [{
	        	xtype : 'textfield',
	        	emptyText : 'Job Name'
	        },"-",{
	        	xtype : 'textfield',
	        	emptyText : 'Trigger Name'
	        },"-",{
	        	xtype : 'datefield',
	        	emptyText : 'Start Time',
	        	format : 'Y-m-d'
	        },"-",{
	        	xtype : 'datefield',
	        	emptyText : 'End Time',
	        	format : 'Y-m-d'
	        },"-",{
	        	text : 'Search',
	        	iconCls : 'glyphicon glyphicon-search',
	        	handler : 'doSearch'
	        },"-",{
	        	text : 'Clear',
	        	iconCls : 'glyphicon glyphicon-erase',
	        	handler : function(){
	        		var items = [0,2,4,6];
	        		for(var i=0; i<items.length; i++){
	        			this.ownerCt.items.items[items[i]].reset();
	        		}
	        	}
	        }]
	    }],
		this.plugins=[{
			ptype: 'rowexpander',
			rowBodyTpl : new Ext.XTemplate(
		        '<p><strong>Message : </strong><font>{message}</font></p>'
			)
		}];
		this.callParent(arguments);
		Ext.QuickTips.init();
	}
});
