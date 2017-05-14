<br/><br/>
	<div class="panel-heading" style="height: 50px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Subscription</h4>
		<div class="input-group pull-right" style="margin-right: -100px; margin-top: -5px">
			<input type="text" name="focus" required class="search-box" placeholder="Search" style="width: 50%;margin-right:0px"/>
			<button class="close-icon redfamily" type="reset"></button>
			<label class="glyphicon glyphicon-search redfamily searchIcon" rel="tooltip" title="Search"></label>
		</div>
	</div>
<g:each in="${userVO.topicSubscriptionList}" var="topic">
<g:if test="${topic.visibility==linksharing.Visibility.PUBLIC}">


	<div class="panel-body">
		<div class="row">
			<div class="col-md-12 col-xs-12 center-block">
					<div class="col-xs-7 col-md-7 center-block"><!--socialConnect-->
					${topic.name}<br/><span class="glyphicon glyphicon-envelope envIcon" style=""></span>

						<select class="form-control whySoSerious pull-left" style="width: 70%;background-color: rgba(0,0,0,0.15);">
							<option value="">SERIOUS</option>
							<option value="">VERY_SERIOUS</option>
							<option value="">CASUAL</option>
						</select>
					</div><!--./socialConnect-->
					<div class="col-xs-3 col-md-3 center-block"><!--socialConnect-->
					Subscriptions<br/>${topic.noOfSubscribedUsers}
					</div><!--./socialConnect-->
					<div class="col-xs-2 col-md-2 pull-right"><!--viewPost-->
						<a href="#" class=" viewPost"> Posts <br/> ${topic.count}</a>
					</div><!--./viewPost-->
			</div>
		</div>
	</div>
</g:if>
</g:each>
