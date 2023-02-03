@color = global i32* null
@str_0 = private unnamed_addr constant [2 x i8] c" \00"
@count = global i32* null
@i = global i32 0
@j = global i32 0
@make = global i32** null
@str_1 = private unnamed_addr constant [3 x i8] c"\n\00"

define internal void @_GLOBAL__sub_I_example.cpp() {
	call void @__global_new_init_color()
	call void @__global_new_init_count()
	ret void

}

define internal void @__global_new_init_color() {
	%1 = call i8* @__malloc(i32 44)
	%2 = bitcast i8* %1 to i32*
	store i32 10, i32* %2
	%3 = getelementptr i32, i32* %2, i32 1
	store i32* %3, i32** @color
	ret void

}

define void @search(i32 %0, i32 %1, i32 %2) {
	%4 = alloca i32
	store i32 %0, i32* %4
	%5 = alloca i32
	store i32 %1, i32* %5
	%6 = alloca i32
	store i32 %2, i32* %6
	%7 = alloca i32
	%8 = alloca i32
	%9 = alloca i32
	%10 = load i32, i32* %5
	%11 = icmp sgt i32 %10, 0
	%12 = load i32, i32* %5
	%13 = icmp slt i32 %12, 0
	%14 = zext i1 %11 to i8
	%15 = zext i1 %13 to i8
	%16 = icmp ne i8 %14, 0
	br i1 %16, label %_lor_next_2, label %_lor_rhs_1

_lor_rhs_1:
	%17 = icmp ne i8 %15, 0
	br label %_lor_next_2

_lor_next_2:
	%18 = icmp ne i1 %16, %17
	%19 = load i32, i32* %4
	%20 = icmp eq i32 %19, 0
	%21 = zext i1 %18 to i8
	%22 = zext i1 %20 to i8
	%23 = icmp ne i8 %21, 0
	br i1 %23, label %_lor_next_4, label %_lor_rhs_3

_lor_rhs_3:
	%24 = icmp ne i8 %22, 0
	br label %_lor_next_4

_lor_next_4:
	%25 = icmp ne i1 %23, %24
	%26 = load i32**, i32*** @make
	%27 = load i32, i32* %4
	%28 = sub nsw i32 %27, 1
	%29 = getelementptr i32*, i32** %26, i32 %28
	%30 = load i32*, i32** %29
	%31 = getelementptr i32, i32* %30, i32 0
	%32 = load i32**, i32*** @make
	%33 = load i32, i32* %4
	%34 = sub nsw i32 %33, 1
	%35 = getelementptr i32*, i32** %32, i32 %34
	%36 = load i32*, i32** %35
	%37 = getelementptr i32, i32* %36, i32 1
	%38 = load i32, i32* %31
	%39 = load i32, i32* %37
	%40 = add nsw i32 %38, %39
	%41 = load i32**, i32*** @make
	%42 = load i32, i32* %4
	%43 = sub nsw i32 %42, 1
	%44 = getelementptr i32*, i32** %41, i32 %43
	%45 = load i32*, i32** %44
	%46 = getelementptr i32, i32* %45, i32 2
	%47 = load i32, i32* %46
	%48 = add nsw i32 %40, %47
	%49 = icmp eq i32 %48, 15
	%50 = zext i1 %25 to i8
	%51 = zext i1 %49 to i8
	%52 = icmp ne i8 %50, 0
	br i1 %52, label %_lor_next_6, label %_lor_rhs_5

_lor_rhs_5:
	%53 = icmp ne i8 %51, 0
	br label %_lor_next_6

_lor_next_6:
	%54 = icmp ne i1 %52, %53
	br i1 %54, label %_if_then_7, label %_if_else_8

_if_then_7:
	%55 = load i32, i32* %4
	%56 = icmp eq i32 %55, 2
	%57 = load i32, i32* %5
	%58 = icmp eq i32 %57, 2
	%59 = zext i1 %56 to i8
	%60 = zext i1 %58 to i8
	%61 = icmp ne i8 %59, 0
	br i1 %61, label %_land_rhs_10, label %_land_next_11

_land_rhs_10:
	%62 = icmp ne i8 %60, 0
	br label %_land_next_11

_land_next_11:
	%63 = icmp eq i1 %61, %62
	br i1 %63, label %_if_then_12, label %_if_else_13

_if_then_12:
	%64 = load i32**, i32*** @make
	%65 = getelementptr i32*, i32** %64, i32 2
	%66 = load i32*, i32** %65
	%67 = getelementptr i32, i32* %66, i32 2
	%68 = load i32, i32* %6
	%69 = sub nsw i32 45, %68
	store i32 %69, i32* %67
	%70 = load i32**, i32*** @make
	%71 = getelementptr i32*, i32** %70, i32 0
	%72 = load i32*, i32** %71
	%73 = getelementptr i32, i32* %72, i32 0
	%74 = load i32**, i32*** @make
	%75 = getelementptr i32*, i32** %74, i32 0
	%76 = load i32*, i32** %75
	%77 = getelementptr i32, i32* %76, i32 1
	%78 = load i32, i32* %73
	%79 = load i32, i32* %77
	%80 = add nsw i32 %78, %79
	%81 = load i32**, i32*** @make
	%82 = getelementptr i32*, i32** %81, i32 0
	%83 = load i32*, i32** %82
	%84 = getelementptr i32, i32* %83, i32 2
	%85 = load i32, i32* %84
	%86 = add nsw i32 %80, %85
	store i32 %86, i32* %7
	%87 = load i32**, i32*** @make
	%88 = getelementptr i32*, i32** %87, i32 1
	%89 = load i32*, i32** %88
	%90 = getelementptr i32, i32* %89, i32 0
	%91 = load i32**, i32*** @make
	%92 = getelementptr i32*, i32** %91, i32 1
	%93 = load i32*, i32** %92
	%94 = getelementptr i32, i32* %93, i32 1
	%95 = load i32, i32* %90
	%96 = load i32, i32* %94
	%97 = add nsw i32 %95, %96
	%98 = load i32**, i32*** @make
	%99 = getelementptr i32*, i32** %98, i32 1
	%100 = load i32*, i32** %99
	%101 = getelementptr i32, i32* %100, i32 2
	%102 = load i32, i32* %101
	%103 = add nsw i32 %97, %102
	%104 = load i32, i32* %7
	%105 = icmp eq i32 %103, %104
	%106 = load i32**, i32*** @make
	%107 = getelementptr i32*, i32** %106, i32 2
	%108 = load i32*, i32** %107
	%109 = getelementptr i32, i32* %108, i32 0
	%110 = load i32**, i32*** @make
	%111 = getelementptr i32*, i32** %110, i32 2
	%112 = load i32*, i32** %111
	%113 = getelementptr i32, i32* %112, i32 1
	%114 = load i32, i32* %109
	%115 = load i32, i32* %113
	%116 = add nsw i32 %114, %115
	%117 = load i32**, i32*** @make
	%118 = getelementptr i32*, i32** %117, i32 2
	%119 = load i32*, i32** %118
	%120 = getelementptr i32, i32* %119, i32 2
	%121 = load i32, i32* %120
	%122 = add nsw i32 %116, %121
	%123 = load i32, i32* %7
	%124 = icmp eq i32 %122, %123
	%125 = zext i1 %105 to i8
	%126 = zext i1 %124 to i8
	%127 = icmp ne i8 %125, 0
	br i1 %127, label %_land_rhs_15, label %_land_next_16

_land_rhs_15:
	%128 = icmp ne i8 %126, 0
	br label %_land_next_16

_land_next_16:
	%129 = icmp eq i1 %127, %128
	%130 = load i32**, i32*** @make
	%131 = getelementptr i32*, i32** %130, i32 0
	%132 = load i32*, i32** %131
	%133 = getelementptr i32, i32* %132, i32 0
	%134 = load i32**, i32*** @make
	%135 = getelementptr i32*, i32** %134, i32 1
	%136 = load i32*, i32** %135
	%137 = getelementptr i32, i32* %136, i32 0
	%138 = load i32, i32* %133
	%139 = load i32, i32* %137
	%140 = add nsw i32 %138, %139
	%141 = load i32**, i32*** @make
	%142 = getelementptr i32*, i32** %141, i32 2
	%143 = load i32*, i32** %142
	%144 = getelementptr i32, i32* %143, i32 0
	%145 = load i32, i32* %144
	%146 = add nsw i32 %140, %145
	%147 = load i32, i32* %7
	%148 = icmp eq i32 %146, %147
	%149 = zext i1 %129 to i8
	%150 = zext i1 %148 to i8
	%151 = icmp ne i8 %149, 0
	br i1 %151, label %_land_rhs_17, label %_land_next_18

_land_rhs_17:
	%152 = icmp ne i8 %150, 0
	br label %_land_next_18

_land_next_18:
	%153 = icmp eq i1 %151, %152
	%154 = load i32**, i32*** @make
	%155 = getelementptr i32*, i32** %154, i32 0
	%156 = load i32*, i32** %155
	%157 = getelementptr i32, i32* %156, i32 1
	%158 = load i32**, i32*** @make
	%159 = getelementptr i32*, i32** %158, i32 1
	%160 = load i32*, i32** %159
	%161 = getelementptr i32, i32* %160, i32 1
	%162 = load i32, i32* %157
	%163 = load i32, i32* %161
	%164 = add nsw i32 %162, %163
	%165 = load i32**, i32*** @make
	%166 = getelementptr i32*, i32** %165, i32 2
	%167 = load i32*, i32** %166
	%168 = getelementptr i32, i32* %167, i32 1
	%169 = load i32, i32* %168
	%170 = add nsw i32 %164, %169
	%171 = load i32, i32* %7
	%172 = icmp eq i32 %170, %171
	%173 = zext i1 %153 to i8
	%174 = zext i1 %172 to i8
	%175 = icmp ne i8 %173, 0
	br i1 %175, label %_land_rhs_19, label %_land_next_20

_land_rhs_19:
	%176 = icmp ne i8 %174, 0
	br label %_land_next_20

_land_next_20:
	%177 = icmp eq i1 %175, %176
	%178 = load i32**, i32*** @make
	%179 = getelementptr i32*, i32** %178, i32 0
	%180 = load i32*, i32** %179
	%181 = getelementptr i32, i32* %180, i32 2
	%182 = load i32**, i32*** @make
	%183 = getelementptr i32*, i32** %182, i32 1
	%184 = load i32*, i32** %183
	%185 = getelementptr i32, i32* %184, i32 2
	%186 = load i32, i32* %181
	%187 = load i32, i32* %185
	%188 = add nsw i32 %186, %187
	%189 = load i32**, i32*** @make
	%190 = getelementptr i32*, i32** %189, i32 2
	%191 = load i32*, i32** %190
	%192 = getelementptr i32, i32* %191, i32 2
	%193 = load i32, i32* %192
	%194 = add nsw i32 %188, %193
	%195 = load i32, i32* %7
	%196 = icmp eq i32 %194, %195
	%197 = zext i1 %177 to i8
	%198 = zext i1 %196 to i8
	%199 = icmp ne i8 %197, 0
	br i1 %199, label %_land_rhs_21, label %_land_next_22

_land_rhs_21:
	%200 = icmp ne i8 %198, 0
	br label %_land_next_22

_land_next_22:
	%201 = icmp eq i1 %199, %200
	%202 = load i32**, i32*** @make
	%203 = getelementptr i32*, i32** %202, i32 0
	%204 = load i32*, i32** %203
	%205 = getelementptr i32, i32* %204, i32 0
	%206 = load i32**, i32*** @make
	%207 = getelementptr i32*, i32** %206, i32 1
	%208 = load i32*, i32** %207
	%209 = getelementptr i32, i32* %208, i32 1
	%210 = load i32, i32* %205
	%211 = load i32, i32* %209
	%212 = add nsw i32 %210, %211
	%213 = load i32**, i32*** @make
	%214 = getelementptr i32*, i32** %213, i32 2
	%215 = load i32*, i32** %214
	%216 = getelementptr i32, i32* %215, i32 2
	%217 = load i32, i32* %216
	%218 = add nsw i32 %212, %217
	%219 = load i32, i32* %7
	%220 = icmp eq i32 %218, %219
	%221 = zext i1 %201 to i8
	%222 = zext i1 %220 to i8
	%223 = icmp ne i8 %221, 0
	br i1 %223, label %_land_rhs_23, label %_land_next_24

_land_rhs_23:
	%224 = icmp ne i8 %222, 0
	br label %_land_next_24

_land_next_24:
	%225 = icmp eq i1 %223, %224
	%226 = load i32**, i32*** @make
	%227 = getelementptr i32*, i32** %226, i32 2
	%228 = load i32*, i32** %227
	%229 = getelementptr i32, i32* %228, i32 0
	%230 = load i32**, i32*** @make
	%231 = getelementptr i32*, i32** %230, i32 1
	%232 = load i32*, i32** %231
	%233 = getelementptr i32, i32* %232, i32 1
	%234 = load i32, i32* %229
	%235 = load i32, i32* %233
	%236 = add nsw i32 %234, %235
	%237 = load i32**, i32*** @make
	%238 = getelementptr i32*, i32** %237, i32 0
	%239 = load i32*, i32** %238
	%240 = getelementptr i32, i32* %239, i32 2
	%241 = load i32, i32* %240
	%242 = add nsw i32 %236, %241
	%243 = load i32, i32* %7
	%244 = icmp eq i32 %242, %243
	%245 = zext i1 %225 to i8
	%246 = zext i1 %244 to i8
	%247 = icmp ne i8 %245, 0
	br i1 %247, label %_land_rhs_25, label %_land_next_26

_land_rhs_25:
	%248 = icmp ne i8 %246, 0
	br label %_land_next_26

_land_next_26:
	%249 = icmp eq i1 %247, %248
	br i1 %249, label %_if_then_27, label %_if_else_28

_if_then_27:
	%250 = load i32*, i32** @count
	%251 = getelementptr i32, i32* %250, i32 0
	%252 = load i32*, i32** @count
	%253 = getelementptr i32, i32* %252, i32 0
	%254 = load i32, i32* %253
	%255 = add nsw i32 %254, 1
	store i32 %255, i32* %251
	store i32 0, i32* %8
	br label %_for_cond_30

_for_cond_30:
	%256 = load i32, i32* %8
	%257 = icmp sle i32 %256, 2
	br i1 %257, label %_for_loop_31, label %_for_next_32

_for_loop_31:
	%258 = load i32, i32* %8
	%259 = add nsw i32 %258, 1
	store i32 %259, i32* %8
	store i32 0, i32* %9
	br label %_for_cond_33

_for_cond_33:
	%260 = load i32, i32* %9
	%261 = icmp sle i32 %260, 2
	br i1 %261, label %_for_loop_34, label %_for_next_35

_for_loop_34:
	%262 = load i32, i32* %9
	%263 = add nsw i32 %262, 1
	store i32 %263, i32* %9
	%264 = load i32**, i32*** @make
	%265 = load i32, i32* %8
	%266 = getelementptr i32*, i32** %264, i32 %265
	%267 = load i32*, i32** %266
	%268 = load i32, i32* %9
	%269 = getelementptr i32, i32* %267, i32 %268
	%270 = load i32, i32* %269
	%271 = call i8* @toString(i32 %270)
	call void @print(i8* %271)
	%272 = getelementptr [2 x i8], [2 x i8]* @str_0, i32 0, i32 0
	call void @print(i8* %272)
	br label %_for_cond_33

_for_next_35:
	%273 = getelementptr [3 x i8], [3 x i8]* @str_1, i32 0, i32 0
	call void @print(i8* %273)
	br label %_for_cond_30

_for_next_32:
	%274 = getelementptr [3 x i8], [3 x i8]* @str_1, i32 0, i32 0
	call void @print(i8* %274)
	br label %_if_next_29

_if_else_28:
	br label %_if_next_29

_if_next_29:
	br label %_if_next_14

_if_else_13:
	%275 = load i32, i32* %5
	%276 = icmp eq i32 %275, 2
	br i1 %276, label %_if_then_36, label %_if_else_37

_if_then_36:
	%277 = load i32**, i32*** @make
	%278 = load i32, i32* %4
	%279 = getelementptr i32*, i32** %277, i32 %278
	%280 = load i32*, i32** %279
	%281 = load i32, i32* %5
	%282 = getelementptr i32, i32* %280, i32 %281
	%283 = load i32**, i32*** @make
	%284 = load i32, i32* %4
	%285 = getelementptr i32*, i32** %283, i32 %284
	%286 = load i32*, i32** %285
	%287 = getelementptr i32, i32* %286, i32 0
	%288 = load i32, i32* %287
	%289 = sub nsw i32 15, %288
	%290 = load i32**, i32*** @make
	%291 = load i32, i32* %4
	%292 = getelementptr i32*, i32** %290, i32 %291
	%293 = load i32*, i32** %292
	%294 = getelementptr i32, i32* %293, i32 1
	%295 = load i32, i32* %294
	%296 = sub nsw i32 %289, %295
	store i32 %296, i32* %282
	%297 = load i32**, i32*** @make
	%298 = load i32, i32* %4
	%299 = getelementptr i32*, i32** %297, i32 %298
	%300 = load i32*, i32** %299
	%301 = load i32, i32* %5
	%302 = getelementptr i32, i32* %300, i32 %301
	%303 = load i32, i32* %302
	%304 = icmp sgt i32 %303, 0
	%305 = load i32**, i32*** @make
	%306 = load i32, i32* %4
	%307 = getelementptr i32*, i32** %305, i32 %306
	%308 = load i32*, i32** %307
	%309 = load i32, i32* %5
	%310 = getelementptr i32, i32* %308, i32 %309
	%311 = load i32, i32* %310
	%312 = icmp slt i32 %311, 10
	%313 = zext i1 %304 to i8
	%314 = zext i1 %312 to i8
	%315 = icmp ne i8 %313, 0
	br i1 %315, label %_land_rhs_39, label %_land_next_40

_land_rhs_39:
	%316 = icmp ne i8 %314, 0
	br label %_land_next_40

_land_next_40:
	%317 = icmp eq i1 %315, %316
	%318 = load i32*, i32** @color
	%319 = load i32**, i32*** @make
	%320 = load i32, i32* %4
	%321 = getelementptr i32*, i32** %319, i32 %320
	%322 = load i32*, i32** %321
	%323 = load i32, i32* %5
	%324 = getelementptr i32, i32* %322, i32 %323
	%325 = load i32, i32* %324
	%326 = getelementptr i32, i32* %318, i32 %325
	%327 = load i32, i32* %326
	%328 = icmp eq i32 %327, 0
	%329 = zext i1 %317 to i8
	%330 = zext i1 %328 to i8
	%331 = icmp ne i8 %329, 0
	br i1 %331, label %_land_rhs_41, label %_land_next_42

_land_rhs_41:
	%332 = icmp ne i8 %330, 0
	br label %_land_next_42

_land_next_42:
	%333 = icmp eq i1 %331, %332
	br i1 %333, label %_if_then_43, label %_if_else_44

_if_then_43:
	%334 = load i32*, i32** @color
	%335 = load i32**, i32*** @make
	%336 = load i32, i32* %4
	%337 = getelementptr i32*, i32** %335, i32 %336
	%338 = load i32*, i32** %337
	%339 = load i32, i32* %5
	%340 = getelementptr i32, i32* %338, i32 %339
	%341 = load i32, i32* %340
	%342 = getelementptr i32, i32* %334, i32 %341
	store i32 1, i32* %342
	%343 = load i32, i32* %5
	%344 = icmp eq i32 %343, 2
	br i1 %344, label %_if_then_46, label %_if_else_47

_if_then_46:
	%345 = load i32, i32* %4
	%346 = add nsw i32 %345, 1
	%347 = load i32**, i32*** @make
	%348 = load i32, i32* %4
	%349 = getelementptr i32*, i32** %347, i32 %348
	%350 = load i32*, i32** %349
	%351 = load i32, i32* %5
	%352 = getelementptr i32, i32* %350, i32 %351
	%353 = load i32, i32* %6
	%354 = load i32, i32* %352
	%355 = add nsw i32 %353, %354
	call void @search(i32 %346, i32 0, i32 %355)
	br label %_if_next_48

_if_else_47:
	%356 = load i32, i32* %4
	%357 = load i32, i32* %5
	%358 = add nsw i32 %357, 1
	%359 = load i32**, i32*** @make
	%360 = load i32, i32* %4
	%361 = getelementptr i32*, i32** %359, i32 %360
	%362 = load i32*, i32** %361
	%363 = load i32, i32* %5
	%364 = getelementptr i32, i32* %362, i32 %363
	%365 = load i32, i32* %6
	%366 = load i32, i32* %364
	%367 = add nsw i32 %365, %366
	call void @search(i32 %356, i32 %358, i32 %367)
	br label %_if_next_48

_if_next_48:
	%368 = load i32*, i32** @color
	%369 = load i32**, i32*** @make
	%370 = load i32, i32* %4
	%371 = getelementptr i32*, i32** %369, i32 %370
	%372 = load i32*, i32** %371
	%373 = load i32, i32* %5
	%374 = getelementptr i32, i32* %372, i32 %373
	%375 = load i32, i32* %374
	%376 = getelementptr i32, i32* %368, i32 %375
	store i32 0, i32* %376
	br label %_if_next_45

_if_else_44:
	br label %_if_next_45

_if_next_45:
	br label %_if_next_38

_if_else_37:
	store i32 1, i32* %8
	br label %_for_cond_49

_for_cond_49:
	%377 = load i32, i32* %8
	%378 = icmp sle i32 %377, 9
	br i1 %378, label %_for_loop_50, label %_for_next_51

_for_loop_50:
	%379 = load i32, i32* %8
	%380 = add nsw i32 %379, 1
	store i32 %380, i32* %8
	%381 = load i32*, i32** @color
	%382 = load i32, i32* %8
	%383 = getelementptr i32, i32* %381, i32 %382
	%384 = load i32, i32* %383
	%385 = icmp eq i32 %384, 0
	br i1 %385, label %_if_then_52, label %_if_else_53

_if_then_52:
	%386 = load i32*, i32** @color
	%387 = load i32, i32* %8
	%388 = getelementptr i32, i32* %386, i32 %387
	store i32 1, i32* %388
	%389 = load i32**, i32*** @make
	%390 = load i32, i32* %4
	%391 = getelementptr i32*, i32** %389, i32 %390
	%392 = load i32*, i32** %391
	%393 = load i32, i32* %5
	%394 = getelementptr i32, i32* %392, i32 %393
	%395 = load i32, i32* %8
	store i32 %395, i32* %394
	%396 = load i32, i32* %5
	%397 = icmp eq i32 %396, 2
	br i1 %397, label %_if_then_55, label %_if_else_56

_if_then_55:
	%398 = load i32, i32* %4
	%399 = add nsw i32 %398, 1
	%400 = load i32, i32* %6
	%401 = load i32, i32* %8
	%402 = add nsw i32 %400, %401
	call void @search(i32 %399, i32 0, i32 %402)
	br label %_if_next_57

_if_else_56:
	%403 = load i32, i32* %4
	%404 = load i32, i32* %5
	%405 = add nsw i32 %404, 1
	%406 = load i32, i32* %6
	%407 = load i32, i32* %8
	%408 = add nsw i32 %406, %407
	call void @search(i32 %403, i32 %405, i32 %408)
	br label %_if_next_57

_if_next_57:
	%409 = load i32**, i32*** @make
	%410 = load i32, i32* %4
	%411 = getelementptr i32*, i32** %409, i32 %410
	%412 = load i32*, i32** %411
	%413 = load i32, i32* %5
	%414 = getelementptr i32, i32* %412, i32 %413
	store i32 0, i32* %414
	%415 = load i32*, i32** @color
	%416 = load i32, i32* %8
	%417 = getelementptr i32, i32* %415, i32 %416
	store i32 0, i32* %417
	br label %_if_next_54

_if_else_53:
	br label %_if_next_54

_if_next_54:
	br label %_for_cond_49

_for_next_51:
	br label %_if_next_38

_if_next_38:
	br label %_if_next_14

_if_next_14:
	br label %_if_next_9

_if_else_8:
	br label %_if_next_9

_if_next_9:
	ret void

}

define internal void @__global_new_init_count() {
	%1 = call i8* @__malloc(i32 8)
	%2 = bitcast i8* %1 to i32*
	store i32 1, i32* %2
	%3 = getelementptr i32, i32* %2, i32 1
	store i32* %3, i32** @count
	ret void

}

define void @origin(i32 %0) {
	%2 = alloca i32
	store i32 %0, i32* %2
	%3 = load i32, i32* %2
	%4 = mul nsw i32 %3, 4
	%5 = add nsw i32 %4, 32
	%6 = call i8* @__malloc(i32 %5)
	%7 = bitcast i8* %6 to i32*
	store i32 %3, i32* %7
	%8 = getelementptr i32, i32* %7, i32 1
	%9 = bitcast i32* %8 to i32**
	store i32** %9, i32*** @make
	store i32 0, i32* @i
	br label %_for_cond_1

_for_cond_1:
	%10 = load i32, i32* @i
	%11 = load i32, i32* %2
	%12 = icmp slt i32 %10, %11
	br i1 %12, label %_for_loop_2, label %_for_next_3

_for_loop_2:
	%13 = load i32, i32* @i
	%14 = add nsw i32 %13, 1
	store i32 %14, i32* @i
	%15 = load i32**, i32*** @make
	%16 = load i32, i32* @i
	%17 = getelementptr i32*, i32** %15, i32 %16
	%18 = load i32, i32* %2
	%19 = mul nsw i32 %18, 4
	%20 = add nsw i32 %19, 32
	%21 = call i8* @__malloc(i32 %20)
	%22 = bitcast i8* %21 to i32*
	store i32 %18, i32* %22
	%23 = getelementptr i32, i32* %22, i32 1
	store i32* %23, i32** %17
	store i32 0, i32* @j
	br label %_for_cond_4

_for_cond_4:
	%24 = load i32, i32* @j
	%25 = load i32, i32* %2
	%26 = icmp slt i32 %24, %25
	br i1 %26, label %_for_loop_5, label %_for_next_6

_for_loop_5:
	%27 = load i32, i32* @j
	%28 = add nsw i32 %27, 1
	store i32 %28, i32* @j
	%29 = load i32**, i32*** @make
	%30 = load i32, i32* @i
	%31 = getelementptr i32*, i32** %29, i32 %30
	%32 = load i32*, i32** %31
	%33 = load i32, i32* @j
	%34 = getelementptr i32, i32* %32, i32 %33
	store i32 0, i32* %34
	br label %_for_cond_4

_for_next_6:
	br label %_for_cond_1

_for_next_3:
	ret void

}

define i32 @main() {
	call void @_GLOBAL__sub_I_example.cpp()
	%1 = load i32*, i32** @count
	%2 = getelementptr i32, i32* %1, i32 0
	store i32 0, i32* %2
	call void @origin(i32 3)
	call void @search(i32 0, i32 0, i32 0)
	%3 = load i32*, i32** @count
	%4 = getelementptr i32, i32* %3, i32 0
	%5 = load i32, i32* %4
	%6 = call i8* @toString(i32 %5)
	call void @println(i8* %6)
	ret i32 0

}

; ModuleID = 'builtin/builtin.c'
source_filename = "builtin/builtin.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @print(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %3)
  ret void
}

declare dso_local i32 @printf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @println(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), i8* %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @printInt(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @printlnInt(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @getString() #0 {
  %1 = alloca i8*, align 8
  %2 = call noalias i8* @malloc(i64 256) #5
  store i8* %2, i8** %1, align 8
  %3 = load i8*, i8** %1, align 8
  %4 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %3)
  %5 = load i8*, i8** %1, align 8
  ret i8* %5
}

; Function Attrs: nounwind
declare dso_local noalias i8* @malloc(i64) #2

declare dso_local i32 @__isoc99_scanf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getInt() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @toString(i32 %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i8*, align 8
  store i32 %0, i32* %2, align 4
  %4 = call noalias i8* @malloc(i64 256) #5
  store i8* %4, i8** %3, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i32, i32* %2, align 4
  %7 = call i32 (i8*, i8*, ...) @sprintf(i8* %5, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32 %6) #5
  %8 = load i8*, i8** %3, align 8
  ret i8* %8
}

; Function Attrs: nounwind
declare dso_local i32 @sprintf(i8*, i8*, ...) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @__mx_length(i8* %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i64 @strlen(i8* %3) #6
  %5 = trunc i64 %4 to i32
  ret i32 %5
}

; Function Attrs: nounwind readonly
declare dso_local i64 @strlen(i8*) #3

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @__mx_substring(i8* %0, i32 %1, i32 %2) #0 {
  %4 = alloca i8*, align 8
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  %8 = alloca i8*, align 8
  store i8* %0, i8** %4, align 8
  store i32 %1, i32* %5, align 4
  store i32 %2, i32* %6, align 4
  %9 = load i32, i32* %6, align 4
  %10 = load i32, i32* %5, align 4
  %11 = sub nsw i32 %9, %10
  %12 = add nsw i32 %11, 1
  store i32 %12, i32* %7, align 4
  %13 = load i32, i32* %7, align 4
  %14 = sext i32 %13 to i64
  %15 = mul i64 1, %14
  %16 = call noalias i8* @malloc(i64 %15) #5
  store i8* %16, i8** %8, align 8
  %17 = load i8*, i8** %8, align 8
  %18 = load i8*, i8** %4, align 8
  %19 = load i32, i32* %5, align 4
  %20 = sext i32 %19 to i64
  %21 = getelementptr inbounds i8, i8* %18, i64 %20
  %22 = load i32, i32* %7, align 4
  %23 = sext i32 %22 to i64
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 1 %17, i8* align 1 %21, i64 %23, i1 false)
  %24 = load i8*, i8** %8, align 8
  %25 = load i32, i32* %7, align 4
  %26 = sub nsw i32 %25, 1
  %27 = sext i32 %26 to i64
  %28 = getelementptr inbounds i8, i8* %24, i64 %27
  store i8 0, i8* %28, align 1
  %29 = load i8*, i8** %8, align 8
  ret i8* %29
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #4

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @__mx_parseInt(i8* %0) #0 {
  %2 = alloca i8*, align 8
  %3 = alloca i32, align 4
  store i8* %0, i8** %2, align 8
  %4 = load i8*, i8** %2, align 8
  %5 = call i32 (i8*, i8*, ...) @__isoc99_sscanf(i8* %4, i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i64 0, i64 0), i32* %3) #5
  %6 = load i32, i32* %3, align 4
  ret i32 %6
}

; Function Attrs: nounwind
declare dso_local i32 @__isoc99_sscanf(i8*, i8*, ...) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @__mx_ord(i8* %0, i32 %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i32, align 4
  store i8* %0, i8** %3, align 8
  store i32 %1, i32* %4, align 4
  %5 = load i8*, i8** %3, align 8
  %6 = load i32, i32* %4, align 4
  %7 = sext i32 %6 to i64
  %8 = getelementptr inbounds i8, i8* %5, i64 %7
  %9 = load i8, i8* %8, align 1
  %10 = sext i8 %9 to i32
  ret i32 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_eq(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp eq i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: nounwind readonly
declare dso_local i32 @strcmp(i8*, i8*) #3

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_ne(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp ne i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_slt(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp slt i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_sle(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp sle i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_sgt(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp sgt i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local signext i8 @__str_sge(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* %5, i8* %6) #6
  %8 = icmp sge i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @__str_add(i8* %0, i8* %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  %5 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %6 = load i8*, i8** %3, align 8
  %7 = call i64 @strlen(i8* %6) #6
  %8 = load i8*, i8** %4, align 8
  %9 = call i64 @strlen(i8* %8) #6
  %10 = add i64 %7, %9
  %11 = add i64 %10, 1
  %12 = mul i64 1, %11
  %13 = call noalias i8* @malloc(i64 %12) #5
  store i8* %13, i8** %5, align 8
  %14 = load i8*, i8** %5, align 8
  %15 = load i8*, i8** %3, align 8
  %16 = call i8* @strcpy(i8* %14, i8* %15) #5
  %17 = load i8*, i8** %5, align 8
  %18 = load i8*, i8** %4, align 8
  %19 = call i8* @strcat(i8* %17, i8* %18) #5
  %20 = load i8*, i8** %5, align 8
  ret i8* %20
}

; Function Attrs: nounwind
declare dso_local i8* @strcpy(i8*, i8*) #2

; Function Attrs: nounwind
declare dso_local i8* @strcat(i8*, i8*) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @__malloc(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = sext i32 %3 to i64
  %5 = call noalias i8* @malloc(i64 %4) #5
  ret i8* %5
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind readonly "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { argmemonly nounwind willreturn }
attributes #5 = { nounwind }
attributes #6 = { nounwind readonly }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.0-4ubuntu1 "}
