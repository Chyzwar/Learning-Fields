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
var seq__10576_10590 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__10577_10591 = null;
var count__10578_10592 = (0);
var i__10579_10593 = (0);
while(true){
if((i__10579_10593 < count__10578_10592)){
var f_10594 = cljs.core._nth.call(null,chunk__10577_10591,i__10579_10593);
cljs.core.println.call(null,"  ",f_10594);

var G__10595 = seq__10576_10590;
var G__10596 = chunk__10577_10591;
var G__10597 = count__10578_10592;
var G__10598 = (i__10579_10593 + (1));
seq__10576_10590 = G__10595;
chunk__10577_10591 = G__10596;
count__10578_10592 = G__10597;
i__10579_10593 = G__10598;
continue;
} else {
var temp__4425__auto___10599 = cljs.core.seq.call(null,seq__10576_10590);
if(temp__4425__auto___10599){
var seq__10576_10600__$1 = temp__4425__auto___10599;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__10576_10600__$1)){
var c__5962__auto___10601 = cljs.core.chunk_first.call(null,seq__10576_10600__$1);
var G__10602 = cljs.core.chunk_rest.call(null,seq__10576_10600__$1);
var G__10603 = c__5962__auto___10601;
var G__10604 = cljs.core.count.call(null,c__5962__auto___10601);
var G__10605 = (0);
seq__10576_10590 = G__10602;
chunk__10577_10591 = G__10603;
count__10578_10592 = G__10604;
i__10579_10593 = G__10605;
continue;
} else {
var f_10606 = cljs.core.first.call(null,seq__10576_10600__$1);
cljs.core.println.call(null,"  ",f_10606);

var G__10607 = cljs.core.next.call(null,seq__10576_10600__$1);
var G__10608 = null;
var G__10609 = (0);
var G__10610 = (0);
seq__10576_10590 = G__10607;
chunk__10577_10591 = G__10608;
count__10578_10592 = G__10609;
i__10579_10593 = G__10610;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_10611 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__5178__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__5178__auto__)){
return or__5178__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_10611);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_10611)))?cljs.core.second.call(null,arglists_10611):arglists_10611));
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
var seq__10580 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__10581 = null;
var count__10582 = (0);
var i__10583 = (0);
while(true){
if((i__10583 < count__10582)){
var vec__10584 = cljs.core._nth.call(null,chunk__10581,i__10583);
var name = cljs.core.nth.call(null,vec__10584,(0),null);
var map__10585 = cljs.core.nth.call(null,vec__10584,(1),null);
var map__10585__$1 = ((((!((map__10585 == null)))?((((map__10585.cljs$lang$protocol_mask$partition0$ & (64))) || (map__10585.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__10585):map__10585);
var doc = cljs.core.get.call(null,map__10585__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__10585__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__10612 = seq__10580;
var G__10613 = chunk__10581;
var G__10614 = count__10582;
var G__10615 = (i__10583 + (1));
seq__10580 = G__10612;
chunk__10581 = G__10613;
count__10582 = G__10614;
i__10583 = G__10615;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__10580);
if(temp__4425__auto__){
var seq__10580__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__10580__$1)){
var c__5962__auto__ = cljs.core.chunk_first.call(null,seq__10580__$1);
var G__10616 = cljs.core.chunk_rest.call(null,seq__10580__$1);
var G__10617 = c__5962__auto__;
var G__10618 = cljs.core.count.call(null,c__5962__auto__);
var G__10619 = (0);
seq__10580 = G__10616;
chunk__10581 = G__10617;
count__10582 = G__10618;
i__10583 = G__10619;
continue;
} else {
var vec__10587 = cljs.core.first.call(null,seq__10580__$1);
var name = cljs.core.nth.call(null,vec__10587,(0),null);
var map__10588 = cljs.core.nth.call(null,vec__10587,(1),null);
var map__10588__$1 = ((((!((map__10588 == null)))?((((map__10588.cljs$lang$protocol_mask$partition0$ & (64))) || (map__10588.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__10588):map__10588);
var doc = cljs.core.get.call(null,map__10588__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__10588__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__10620 = cljs.core.next.call(null,seq__10580__$1);
var G__10621 = null;
var G__10622 = (0);
var G__10623 = (0);
seq__10580 = G__10620;
chunk__10581 = G__10621;
count__10582 = G__10622;
i__10583 = G__10623;
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
