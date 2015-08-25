// Compiled by ClojureScript 1.7.48 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
cljs.repl.print_doc = (function cljs$repl$print_doc(m){
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,[cljs.core.str((function (){var temp__4425__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__4425__auto__)){
var ns = temp__4425__auto__;
return [cljs.core.str(ns),cljs.core.str("/")].join('');
} else {
return null;
}
})()),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Protocol");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m))){
var seq__12635_12649 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__12636_12650 = null;
var count__12637_12651 = (0);
var i__12638_12652 = (0);
while(true){
if((i__12638_12652 < count__12637_12651)){
var f_12653 = cljs.core._nth.call(null,chunk__12636_12650,i__12638_12652);
cljs.core.println.call(null,"  ",f_12653);

var G__12654 = seq__12635_12649;
var G__12655 = chunk__12636_12650;
var G__12656 = count__12637_12651;
var G__12657 = (i__12638_12652 + (1));
seq__12635_12649 = G__12654;
chunk__12636_12650 = G__12655;
count__12637_12651 = G__12656;
i__12638_12652 = G__12657;
continue;
} else {
var temp__4425__auto___12658 = cljs.core.seq.call(null,seq__12635_12649);
if(temp__4425__auto___12658){
var seq__12635_12659__$1 = temp__4425__auto___12658;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12635_12659__$1)){
var c__5962__auto___12660 = cljs.core.chunk_first.call(null,seq__12635_12659__$1);
var G__12661 = cljs.core.chunk_rest.call(null,seq__12635_12659__$1);
var G__12662 = c__5962__auto___12660;
var G__12663 = cljs.core.count.call(null,c__5962__auto___12660);
var G__12664 = (0);
seq__12635_12649 = G__12661;
chunk__12636_12650 = G__12662;
count__12637_12651 = G__12663;
i__12638_12652 = G__12664;
continue;
} else {
var f_12665 = cljs.core.first.call(null,seq__12635_12659__$1);
cljs.core.println.call(null,"  ",f_12665);

var G__12666 = cljs.core.next.call(null,seq__12635_12659__$1);
var G__12667 = null;
var G__12668 = (0);
var G__12669 = (0);
seq__12635_12649 = G__12666;
chunk__12636_12650 = G__12667;
count__12637_12651 = G__12668;
i__12638_12652 = G__12669;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_12670 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__5178__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__5178__auto__)){
return or__5178__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_12670);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_12670)))?cljs.core.second.call(null,arglists_12670):arglists_12670));
}
} else {
}
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"special-form","special-form",-1326536374).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Special Form");

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.contains_QMARK_.call(null,m,new cljs.core.Keyword(null,"url","url",276297046))){
if(cljs.core.truth_(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/"),cljs.core.str(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))].join(''));
} else {
return null;
}
} else {
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/special_forms#"),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Macro");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"REPL Special Function");
} else {
}

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
var seq__12639 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__12640 = null;
var count__12641 = (0);
var i__12642 = (0);
while(true){
if((i__12642 < count__12641)){
var vec__12643 = cljs.core._nth.call(null,chunk__12640,i__12642);
var name = cljs.core.nth.call(null,vec__12643,(0),null);
var map__12644 = cljs.core.nth.call(null,vec__12643,(1),null);
var map__12644__$1 = ((((!((map__12644 == null)))?((((map__12644.cljs$lang$protocol_mask$partition0$ & (64))) || (map__12644.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__12644):map__12644);
var doc = cljs.core.get.call(null,map__12644__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__12644__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__12671 = seq__12639;
var G__12672 = chunk__12640;
var G__12673 = count__12641;
var G__12674 = (i__12642 + (1));
seq__12639 = G__12671;
chunk__12640 = G__12672;
count__12641 = G__12673;
i__12642 = G__12674;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__12639);
if(temp__4425__auto__){
var seq__12639__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12639__$1)){
var c__5962__auto__ = cljs.core.chunk_first.call(null,seq__12639__$1);
var G__12675 = cljs.core.chunk_rest.call(null,seq__12639__$1);
var G__12676 = c__5962__auto__;
var G__12677 = cljs.core.count.call(null,c__5962__auto__);
var G__12678 = (0);
seq__12639 = G__12675;
chunk__12640 = G__12676;
count__12641 = G__12677;
i__12642 = G__12678;
continue;
} else {
var vec__12646 = cljs.core.first.call(null,seq__12639__$1);
var name = cljs.core.nth.call(null,vec__12646,(0),null);
var map__12647 = cljs.core.nth.call(null,vec__12646,(1),null);
var map__12647__$1 = ((((!((map__12647 == null)))?((((map__12647.cljs$lang$protocol_mask$partition0$ & (64))) || (map__12647.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__12647):map__12647);
var doc = cljs.core.get.call(null,map__12647__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__12647__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__12679 = cljs.core.next.call(null,seq__12639__$1);
var G__12680 = null;
var G__12681 = (0);
var G__12682 = (0);
seq__12639 = G__12679;
chunk__12640 = G__12680;
count__12641 = G__12681;
i__12642 = G__12682;
continue;
}
} else {
return null;
}
}
break;
}
} else {
return null;
}
}
});

//# sourceMappingURL=repl.js.map