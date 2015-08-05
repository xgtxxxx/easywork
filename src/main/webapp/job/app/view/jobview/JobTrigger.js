Ext.define('Job.view.jobview.JobTrigger', {
	extend : 'Job.view.common.OperateGrid',
	alias : 'widget.jobtrigger',
	store : 'JobTriggerStore',
	dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'JobTriggerStore', // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }],
	initComponent : function() {
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
	        	 scope : this,
	             getClass:function(v,m,r,rIndex,cIndex,store){ 
	            	 var action = this.down('actioncolumn');
	                 if (v=='PAUSED'){ 
	                	 m['tdAttr'] = 'data-qtip="Resume"';
	                	 action.items[0].handler = this.resumeTrigger;
	                     return 'icon-resume';  
	                 }
	                 if (v=='NORMAL'){ 
	                	 m['tdAttr'] = 'data-qtip="Pause"';
	                	 action.items[0].handler = this.pauseTrigger;
	                     return 'icon-pause';  
	                 } 
	             } 
	         }
	         ,{
	             iconCls: 'icon-delete', 
	             tooltip: 'Delete',
	             scope  : this,
	             handler: this.deleteTrigger
	         }]
		}];
		this.tbar = Ext.create('Ext.toolbar.Toolbar',{
			items : [{
				xtype : 'label',
				text : 'Triggers[]'
			}, '->', {
				text : 'PauseAll',
				iconCls : 'icon-pause',
				scope : this,
				handler : this.pauseAll
			}, '-', {
				text : 'ResumeAll',
				iconCls : 'icon-resume',
				scope : this,
				handler : this.resumeAll
			}, '-', {
				text : 'NewTrigger',
				iconCls : 'icon-add',
				scope : this,
				handler : this.newTrigger
			},'-',{
				text : 'Back',
				iconCls : 'icon-back',
				scope : this,
				handler : this.goBack
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
		this.getDockedItems('toolbar[dock="top"]')[0].items.items[0].setText('Triggers['+jobName+']');
		Ext.apply(this.store.proxy.extraParams,{
			jobName : jobName
		});
		this.store.load()
	},
	goBack : function(){
		this.ownerCt.getLayout().setActiveItem(0);
	},
	pauseAll : function(){
		var grid = this;
		grid.confirm("Sure to pause all?",function(){
			var jobName = grid.getJobname();
			grid.doRequest({jobName:jobName},"/job/pauseJob.do")
		})
	},
	resumeAll : function(){
		var grid = this;
		grid.confirm("Sure to resume all?",function(){
			var jobName = grid.getJobname();
			grid.doRequest({jobName:jobName},"/job/resumeJob.do")
		})
	},
	newTrigger : function(){
		var grid = this;
		var jobName = this.getJobname();
		var win = Ext.create("Ext.window.Window",{
			width : 400,
			height : 240,
			title : 'New Trigger',
			layout : 'fit',
			frame : false,
			modal : true,
			items : [{
				xtype : 'triggerform',
				border : false
			}],
			buttonAlign : 'center',
			buttons : [{
				text : 'Save',
				handler : function(){
					var form = win.down('triggerform').getForm();
					if (form.isValid()) {
		                form.submit({
		                	url: CTX.PATH+'/job/newTrigger.do',
		                    success: function(form, action) {
		                       Ext.Msg.alert('Success', action.result.message);
		                       grid.getStore().reload();
		                       win.close();
		                    },
		                    failure: function(form, action) {
		                        Ext.Msg.alert('Failed', action.result ? action.result.message : 'No response');
		                    }
		                });
		            }
				}
			},{
				text : 'Cancel',
				handler : function(){
					win.close();
				}
			}]
		});
		win.down('triggerform').setJobName(jobName);
		win.show();
	},
	pauseTrigger : function(grid, rowIndex, colIndex){
		var p = this;
		var record = grid.getStore().getAt(rowIndex);
		p.confirm("Sure to pause "+record.data.name+"?",function(){
			p.doRequest({triggerName:record.data.name},"/job/pauseTrigger.do")
		})
	},
	resumeTrigger : function(grid, rowIndex, colIndex){
		var p = this;
		var record = grid.getStore().getAt(rowIndex);
		p.confirm("Sure to resume "+record.data.name+"?",function(){
			p.doRequest({triggerName:record.data.name},"/job/resumeTrigger.do")
		})
	},
	deleteTrigger : function(grid, rowIndex, colIndex){
		var p = this;
		var record = grid.getStore().getAt(rowIndex);
		p.confirm("Sure to remove "+record.data.name+"?",function(){
			p.doRequest({triggerName:record.data.name},"/job/removeTrigger.do")
		})
	}
});
