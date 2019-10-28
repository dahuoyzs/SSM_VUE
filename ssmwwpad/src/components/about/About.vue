<template>
	<div>
		<div class="row mb-5">
			<div class="col-4">
				<!-- 导航 -->
				<div class="list-group mb-5">
					<router-link tag="li" class="nav-link" :to="{name:'aboutone'}">
						<a class="list-group-item list-group-item-action">第一页</a>
					</router-link>
					<router-link tag="li" class="nav-link" :to="{name:'abouttwo'}">
						<a class="list-group-item list-group-item-action">第二页</a>
					</router-link>
					<router-link tag="li" class="nav-link" :to="{name:'aboutthree'}">
						<a class="list-group-item list-group-item-action">第三页</a>
					</router-link>
					<router-link tag="li" class="nav-link" :to="{name:'aboutfour'}">
						<a class="list-group-item list-group-item-action">第四页</a>
					</router-link>
				</div>
			</div>
			<div class="col-8">
				<!-- 相应的内容 -->
				<router-view></router-view>
			</div>
		</div>
	</div>
</template>