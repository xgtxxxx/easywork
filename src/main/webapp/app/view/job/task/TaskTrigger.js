Ext.define('app.view.job.task.TaskTrigger', {
	extend : 'app.view.common.OperateGrid',
	alias : 'widget.tasktrigger',
	requires : ['app.view.job.task.TaskTriggerController'],
	controller : 'tasktrigger',
	uses : ['app.store.job.TaskTriggerStore'],
	initComponent : function() {
		this.store = Ext.create('app.store.job.TaskTriggerStore');
		this.dockedItems = [{
	        xtype: 'pagingtoolbar',
	        store: this.store, // same store GridPanel is using
	        dock: 'bottom',
	        displayInfo: true
	    }],
		this.columns = [ {
			xtype : 'rownumberer'
		}, {
			header : 'Name',
			dataIndex : 'name'
		}, {
			header : 'State',
			dataIndex : 'state',
			renderer : function(value, metaData, record, rowIndex,
					colIndex, store, view) {
				var color = 'green';
				if(value=='PAUSED'){
					color = 'red';
				}
				return '<font color='+color+'>'+value+'</font>';
			}
		}, {
			header : 'Type',
			dataIndex : 'type'
		}, {
			header : 'Priority',
			dataIndex : 'priority',
			width : 50
		}, {
			header : 'Cron',
			dataIndex : 'cron'
		}, {
			header : 'RC',
			dataIndex : 'repectCount',
			width : 50
		}, {
			header : 'RI',
			dataIndex : 'repectInterval',
			width : 50
		}, {
			header : 'Start Time',
			dataIndex : 'startTime',
			xtype: 'datecolumn',   
			format:'Y-m-d H:i:s',
			width : 150
		}, {
			header : 'End Time',
			dataIndex : 'endTime',
			xtype: 'datecolumn',   
			format:'Y-m-d H:i:s',
			width : 150
		}, {
			header : 'Next Fire Time',
			dataIndex : 'nextFireTime',
			xtype: 'datecolumn',   
			format:'Y-m-d H:i:s',
			width : 150
		}, {
			header : 'Previous Fire Time',
			dataIndex : 'previousFireTime',
			xtype: 'datecolumn',   
			format:'Y-m-d H:i:s',
			width : 150
		}, {
			 xtype:'actioncolumn',
			 dataIndex : 'state',
	         items: [{
	             getClass:function(v,m,r,rIndex,cIndex,store){ 
	            	 var action = v;
	                 if (v=='PAUSED'){ 
	                	 m['tdAttr'] = 'data-qtip="Resume"';
	                	 this.items[0].handler = "resumeTrigger";
	                     return 'icon-resume';  
	                 }
	                 if (v=='NORMAL'){ 
	                	 m['tdAttr'] = 'data-qtip="Pause"';
	                	 this.items[0].handler = "pauseTrigger";
	                     return 'icon-pause';  
	                 } 
	             } 
	         },{
	             iconCls: 'icon-delete', 
	             tooltip: 'Delete',
	             handler: 'deleteTrigger'
	         }]
		}];
		this.tbar = Ext.create('Ext.toolbar.Toolbar',{
			items : [{
				xtype : 'label',
				text : 'Triggers[]'
			}, '->', {
				text : 'PauseAll',
				iconCls : 'icon-pause',
				handler : 'pauseAll'
			}, '-', {
				text : 'ResumeAll',
				iconCls : 'icon-resume',
				handler : 'resumeAll'
			}, '-', {
				text : 'NewTrigger',
				iconCls : 'icon-add',
				handler : 'newTrigger'
			},'-',{
				text : 'Back',
				iconCls : 'icon-back',
				handler : 'goBack'
			} ]
		});
		this.callParent(arguments);
		Ext.QuickTips.init();
	},
	getJobname : function(){
		var jobName = this.store.proxy.extraParams.jobName;
		return jobName;
	},
	reload : function(jobName) {
		var me = this;
		me.down('toolbar').items.items[0].setText('Triggers['+jobName+']');
		Ext.apply(me.store.proxy.extraParams,{
			jobName : jobName
		});
		me.store.load()
	},
});
