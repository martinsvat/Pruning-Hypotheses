//package logicStuff.learning.constraints.shortConstraintLearner.developments;
//
//import ida.ilp.logic.Clause;
//import ida.ilp.logic.special.IsoClauseWrapper;
//import logicStuff.theories.TheorySimplifier;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * Created by Admin on 08.06.2017.
// */
//public class Debugger{
//
//
//    public static String queriedMemorized = "2_bond(V2, V1), !2_bond(V1, V2)\n" +
//            "ar_bond(V2, V1), !ar_bond(V1, V2)\n" +
//            "1_bond(V2, V1), !1_bond(V1, V2)\n" +
//            "3_bond(V1, V2), !3_bond(V2, V1)\n" +
//            "am_bond(V2, V1), !am_bond(V1, V2)\n" +
//            "!eu(V1), !as(V2)\n" +
//            "!fe(V1), !pb(V2)\n" +
//            "!nar(V1), !ge(V1)\n" +
//            "!n3(V1), !se(V1)\n" +
//            "!am_bond(V1, V2), !au(V2)\n" +
//            "!car(V1), !p3(V1)\n" +
//            "!au(V1), !pb(V1)\n" +
//            "!pd(V1), !as(V1)\n" +
//            "!au(V1), !ar_bond(V1, V2)\n" +
//            "!nar(V1), !as(V1)\n" +
//            "!o3(V1), !n2(V1)\n" +
//            "!n3(V1), !o2(V1)\n" +
//            "!cl(V1), !pb(V2)\n" +
//            "!ge(V1), !f(V2)\n" +
//            "!1_bond(V1, V2), !n1(V2)\n" +
//            "!p3(V1), !se(V2)\n" +
//            "!am_bond(V1, V2), !o3(V2)\n" +
//            "!nar(V1), !c2(V1)\n" +
//            "!au(V1), !nam(V1)\n" +
//            "!pd(V1), !c2(V1)\n" +
//            "!se(V1), !hg(V2)\n" +
//            "!ge(V1), !so2(V2)\n" +
//            "!ar_bond(V1, V2), !zn(V2)\n" +
//            "!pd(V1), !ge(V1)\n" +
//            "!f(V1), !n2(V1)\n" +
//            "!eu(V1), !s2(V1)\n" +
//            "!c1(V1), !pb(V1)\n" +
//            "!car(V1), !s2(V1)\n" +
//            "!hg(V1), !cu(V1)\n" +
//            "!pt(V1), !cu(V1)\n" +
//            "!ni(V1), !p3(V1)\n" +
//            "!2_bond(V2, V1), !c3(V1)\n" +
//            "!cu(V1), !br(V1)\n" +
//            "!n2(V1), !i(V2)\n" +
//            "!pt(V1), !so2(V1)\n" +
//            "!so(V1), !au(V2)\n" +
//            "!ar_bond(V1, V2), !s3(V2)\n" +
//            "!i(V1), !c2(V1)\n" +
//            "!as(V1), !f(V1)\n" +
//            "!2_bond(V1, V2), !ru(V1)\n" +
//            "!pd(V1), !n2(V1)\n" +
//            "!n4(V1), !pb(V1)\n" +
//            "!sn(V1), !as(V2)\n" +
//            "!3_bond(V1, V2), !au(V2)\n" +
//            "!c1(V1), !nam(V1)\n" +
//            "!hg(V1), !cr(V1)\n" +
//            "!ni(V1), !pb(V2)\n" +
//            "!am_bond(V1, V2), !1_bond(V1, V2)\n" +
//            "!1_bond(V2, V1), !am_bond(V1, V2)\n" +
//            "!nar(V1), !n2(V1)\n" +
//            "!n4(V1), !ar_bond(V1, V2)\n" +
//            "!cr(V1), !br(V1)\n" +
//            "!hg(V1), !o2(V2)\n" +
//            "!sn(V1), !pt(V2)\n" +
//            "!ge(V1), !eu(V2)\n" +
//            "!f(V1), !ge(V1)\n" +
//            "!pt(V1), !ar_bond(V1, V3)\n" +
//            "!n3(V1), !npl3(V1)\n" +
//            "!f(V1), !c2(V1)\n" +
//            "!s2(V1), !ru(V2)\n" +
//            "!ar_bond(V2, V1), !c1(V1)\n" +
//            "!pt(V1), !cr(V1)\n" +
//            "!ru(V1), !c3(V1)\n" +
//            "!eu(V1), !p3(V1)\n" +
//            "!au(V1), !as(V1)\n" +
//            "!npl3(V1), !c3(V1)\n" +
//            "!am_bond(V1, V2), !so(V2)\n" +
//            "!p3(V1), !ru(V2)\n" +
//            "!n3(V1), !3_bond(V1, V3)\n" +
//            "!ru(V1), !hg(V2)\n" +
//            "!s3(V1), !br(V1)\n" +
//            "!hg(V1), !s3(V1)\n" +
//            "!pt(V1), !s3(V1)\n" +
//            "!pt(V1), !cl(V1)\n" +
//            "!ge(V1), !i(V2)\n" +
//            "!o3(V1), !au(V2)\n" +
//            "!au(V1), !ge(V1)\n" +
//            "!i(V1), !n2(V1)\n" +
//            "!au(V1), !c2(V1)\n" +
//            "!so(V1), !so(V2)\n" +
//            "!sn(V1), !s2(V1)\n" +
//            "!n4(V1), !nam(V1)\n" +
//            "!n1(V1), !pb(V1)\n" +
//            "!ru(V1), !oco2(V2)\n" +
//            "!ge(V1), !s3(V2)\n" +
//            "!hg(V1), !cl(V1)\n" +
//            "!ge(V1), !cr(V2)\n" +
//            "!n3(V1), !eu(V1)\n" +
//            "!cu(V1), !p3(V1)\n" +
//            "!zn(V1), !nam(V1)\n" +
//            "!ge(V1), !n4(V2)\n" +
//            "!eu(V1), !npl3(V2)\n" +
//            "!s3(V1), !s2(V1)\n" +
//            "!cl(V1), !as(V2)\n" +
//            "!fe(V1), !as(V2)\n" +
//            "!ru(V1), !se(V1)\n" +
//            "!oco2(V1), !c2(V1)\n" +
//            "!pd(V1), !ar_bond(V1, V2)\n" +
//            "!n1(V1), !nam(V1)\n" +
//            "!so2(V1), !p3(V1)\n" +
//            "!am_bond(V2, V1), !n2(V1)\n" +
//            "!ge(V1), !n1(V2)\n" +
//            "!pt(V1), !zn(V2)\n" +
//            "!so(V1), !c2(V1)\n" +
//            "!c3(V1), !sn(V1)\n" +
//            "!ni(V1), !nar(V2)\n" +
//            "!ar_bond(V1, V2), !so2(V2)\n" +
//            "!hg(V1), !c1(V1)\n" +
//            "!br(V1), !zn(V2)\n" +
//            "!so(V1), !ge(V1)\n" +
//            "!i(V1), !so(V2)\n" +
//            "!hg(V1), !zn(V2)\n" +
//            "!cd(V1), cl(V2)\n" +
//            "!3_bond(V1, V3), !p3(V1)\n" +
//            "!c1(V1), !br(V1)\n" +
//            "!f(V1), !so(V2)\n" +
//            "!cl(V1), !s2(V1)\n" +
//            "!c1(V1), !pt(V1)\n" +
//            "!cr(V1), !p3(V1)\n" +
//            "!ar_bond(V1, V2), !fe(V2)\n" +
//            "!s3(V1), !p3(V1)\n" +
//            "!oco2(V1), !n2(V1)\n" +
//            "!sn(V1), !hg(V2)\n" +
//            "!3_bond(V1, V2), !oco2(V1)\n" +
//            "!3_bond(V2, V1), !am_bond(V1, V2)\n" +
//            "!ru(V1), !o2(V1)\n" +
//            "!3_bond(V1, V2), !am_bond(V1, V2)\n" +
//            "!pd(V1), !pb(V1)\n" +
//            "!ge(V1), !cl(V2)\n" +
//            "!nar(V1), !au(V2)\n" +
//            "!cu(V1), !pb(V2)\n" +
//            "!npl3(V1), !s2(V1)\n" +
//            "!cd(V1), !au(V2)\n" +
//            "!n2(V1), !cu(V2)\n" +
//            "!br(V1), !so2(V1)\n" +
//            "!fe(V1), !s2(V1)\n" +
//            "!3_bond(V2, V1), !so(V1)\n" +
//            "!se(V1), !br(V2)\n" +
//            "!n4(V1), !br(V1)\n" +
//            "!o3(V1), !as(V1)\n" +
//            "!hg(V1), !so2(V1)\n" +
//            "!2_bond(V1, V2), !n3(V1)\n" +
//            "!ni(V1), !pt(V2)\n" +
//            "!so2(V1), !pb(V2)\n" +
//            "!s2(V1), !se(V2)\n" +
//            "!n2(V1), !cr(V2)\n" +
//            "!3_bond(V1, V3), !s2(V1)\n" +
//            "!n4(V1), !hg(V1)\n" +
//            "!n1(V1), !pt(V1)\n" +
//            "!car(V1), !br(V1)\n" +
//            "!ni(V1), !as(V2)\n" +
//            "!n4(V1), !pt(V1)\n" +
//            "!se(V1), !c3(V1)\n" +
//            "!o3(V1), !ge(V1)\n" +
//            "!fe(V1), !p3(V1)\n" +
//            "!n1(V1), !hg(V1)\n" +
//            "!hg(V1), !car(V1)\n" +
//            "!3_bond(V1, V2), !c2(V1)\n" +
//            "!cl(V1), !p3(V1)\n" +
//            "!n3(V1), !sn(V1)\n" +
//            "!so(V1), !n2(V1)\n" +
//            "!cr(V1), !pb(V2)\n" +
//            "!nar(V1), !nam(V1)\n" +
//            "!car(V1), !pt(V1)\n" +
//            "!cd(V1), !o3(V2)\n" +
//            "!nam(V1), !so2(V1)\n" +
//            "!ge(V1), !fe(V2)\n" +
//            "!i(V1), !au(V2)\n" +
//            "!zn(V1), !br(V1)\n" +
//            "!sn(V1), !nar(V2)\n" +
//            "!oco2(V1), !as(V1)\n" +
//            "!zn(V1), !hg(V1)\n" +
//            "!se(V1), !oco2(V2)\n" +
//            "!ar_bond(V1, V2), !eu(V2)\n" +
//            "!3_bond(V2, V1), !ge(V1)\n" +
//            "!o2(V1), !c3(V1)\n" +
//            "!cd(V1), !so(V2)\n" +
//            "!n1(V1), !br(V1)\n" +
//            "!o3(V1), !c2(V1)\n" +
//            "!cr(V1), !nam(V1)\n" +
//            "!zn(V1), !pt(V1)\n" +
//            "!ni(V1), !s2(V1)\n" +
//            "!f(V1), !nam(V1)\n" +
//            "!3_bond(V1, V2), !as(V1)\n" +
//            "!cu(V1), !nam(V1)\n" +
//            "!2_bond(V1, V3), !c3(V1)\n" +
//            "!hg(V2), !2_bond(V1, V2)\n" +
//            "!oco2(V1), !ge(V1)\n" +
//            "!i(V1), !nam(V1)\n" +
//            "!c1(V1), !cd(V2)\n" +
//            "!f(V1), !au(V2)\n" +
//            "!s3(V1), !pb(V2)\n" +
//            "!nar(V1), !pt(V1)\n" +
//            "!pb(V1), !cl(V1)\n" +
//            "!hg(V1), !nar(V1)\n" +
//            "!ru(V1), !as(V2)\n" +
//            "!ar_bond(V1, V2), !n4(V2)\n" +
//            "!n4(V1), !n2(V1)\n" +
//            "!pt(V1), !ru(V2)\n" +
//            "!cd(V1), !fe(V2)\n" +
//            "!o3(V1), !cd(V1)\n" +
//            "!ar_bond(V1, V2), !cu(V2)\n" +
//            "!car(V1), !cd(V2)\n" +
//            "!n1(V1), !n2(V1)\n" +
//            "!au(V1), !au(V2)\n" +
//            "!nar(V1), !br(V1)\n" +
//            "!ru(V1), !c2(V2)\n" +
//            "!pb(V1), !fe(V1)\n" +
//            "!ar_bond(V1, V2), !so(V1)\n" +
//            "!se(V1), !pb(V2)\n" +
//            "!oco2(V1), !pb(V1)\n" +
//            "!ni(V1), !cd(V2)\n" +
//            "!pd(V1), !pd(V2)\n" +
//            "!so(V1), !nam(V1)\n" +
//            "!so(V1), !cr(V2)\n" +
//            "!as(V1), !cu(V1)\n" +
//            "!am_bond(V1, V2), !oco2(V1)\n" +
//            "!zn(V1), !n2(V1)\n" +
//            "!br(V1), !ru(V2)\n" +
//            "!ru(V1), !nam(V2)\n" +
//            "!hg(V1), !sn(V1)\n" +
//            "!pd(V1), !cd(V1)\n" +
//            "!cu(V1), !ge(V1)\n" +
//            "!ar_bond(V1, V2), !cr(V2)\n" +
//            "!ru(V1), !s2(V1)\n" +
//            "!cu(V1), !c2(V1)\n" +
//            "!as(V1), !cr(V1)\n" +
//            "!sn(V1), !oco2(V2)\n" +
//            "!pd(V1), !3_bond(V2, V1)\n" +
//            "!car(V1), !am_bond(V1, V3)\n" +
//            "!ru(V1), !p3(V1)\n" +
//            "!so2(V1), !hg(V2)\n" +
//            "!am_bond(V1, V2), !as(V1)\n" +
//            "!c1(V1), !au(V2)\n" +
//            "!cr(V1), !ge(V1)\n" +
//            "!cl(V2), !ar_bond(V1, V2)\n" +
//            "!br(V1), !sn(V1)\n" +
//            "!car(V1), !pb(V1)\n" +
//            "!ni(V1), !pb(V1)\n" +
//            "!s2(V1), !br(V2)\n" +
//            "!3_bond(V2, V1), !nar(V1)\n" +
//            "!so(V1), !n1(V2)\n" +
//            "!eu(V1), !cd(V2)\n" +
//            "!ge(V1), !o2(V2)\n" +
//            "!pt(V1), !sn(V1)\n" +
//            "!cr(V1), !c2(V1)\n" +
//            "!sn(V1), !n2(V2)\n" +
//            "!npl3(V1), !pb(V2)\n" +
//            "!as(V1), !s3(V1)\n" +
//            "!ar_bond(V1, V2), !o3(V1)\n" +
//            "!n4(V1), !au(V2)\n" +
//            "!eu(V1), !oco2(V2)\n" +
//            "!s3(V1), !c2(V1)\n" +
//            "!c3(V1), !s2(V1)\n" +
//            "!p3(V1), !br(V2)\n" +
//            "!cd(V1), !i(V1)\n" +
//            "!ar_bond(V1, V2), !n1(V2)\n" +
//            "!c3(V1), !so2(V1)\n" +
//            "!so(V1), !o3(V1)\n" +
//            "!s3(V1), !ge(V1)\n" +
//            "!i(V1), !pd(V2)\n" +
//            "!npl3(V1), !p3(V1)\n" +
//            "!am_bond(V2, V1), !ge(V1)\n" +
//            "!as(V1), !cl(V1)\n" +
//            "!so2(V1), !s2(V1)\n" +
//            "!as(V1), !fe(V1)\n" +
//            "!cu(V1), !n2(V1)\n" +
//            "!n3(V1), !as(V2)\n" +
//            "!c1(V1), !so(V2)\n" +
//            "!ge(V1), !cl(V1)\n" +
//            "!so2(V2), !am_bond(V1, V2)\n" +
//            "!o3(V1), !cr(V2)\n" +
//            "!cd(V1), !f(V1)\n" +
//            "!ge(V1), !sn(V2)\n" +
//            "!nar(V1), !cd(V2)\n" +
//            "!sn(V1), !p3(V1)\n" +
//            "!se(V1), !as(V2)\n" +
//            "!br(V1), !cl(V1)\n" +
//            "!f(V1), !n1(V2)\n" +
//            "!i(V1), !cl(V2)\n" +
//            "!pt(V1), !fe(V1)\n" +
//            "!ar_bond(V1, V2), !i(V2)\n" +
//            "!hg(V1), !fe(V1)\n" +
//            "!br(V1), !fe(V1)\n" +
//            "!eu(V1), !pb(V2)\n" +
//            "!c1(V1), !as(V1)\n" +
//            "!o3(V1), !nam(V1)\n" +
//            "!cu(V1), !cd(V2)\n" +
//            "!ar_bond(V1, V2), !pd(V2)\n" +
//            "!ge(V1), !zn(V2)\n" +
//            "!n3(V1), !ru(V1)\n" +
//            "!zn(V1), !pb(V1)\n" +
//            "!n2(V1), !eu(V2)\n" +
//            "!se(V1), !s2(V1)\n" +
//            "!ge(V1), !ni(V2)\n" +
//            "!pd(V1), !nam(V1)\n" +
//            "!c1(V1), !ge(V1)\n" +
//            "!o3(V1), !i(V1)\n" +
//            "!c1(V1), !c2(V1)\n" +
//            "!pt(V1), !se(V2)\n" +
//            "!ar_bond(V1, V2), !cd(V1)\n" +
//            "!o2(V1), !s2(V1)\n" +
//            "!cd(V1), !cr(V2)\n" +
//            "!i(V1), !f(V1)\n" +
//            "!ni(V1), !oco2(V2)\n" +
//            "!s3(V1), !cd(V2)\n" +
//            "!oco2(V1), !nar(V1)\n" +
//            "!ar_bond(V1, V2), !f(V2)\n" +
//            "!3_bond(V1, V2), !zn(V2)\n" +
//            "!am_bond(V1, V2), !nar(V1)\n" +
//            "!cd(V1), !n4(V2)\n" +
//            "!so(V1), !cd(V1)\n" +
//            "!au(V1), !n2(V1)\n" +
//            "!n4(V1), !as(V1)\n" +
//            "!nar(V1), !pb(V1)\n" +
//            "!so2(V1), !as(V2)\n" +
//            "!i(V1), !fe(V2)\n" +
//            "!pd(V1), !au(V2)\n" +
//            "!pt(V1), !ni(V1)\n" +
//            "!pt(V1), !npl3(V1)\n" +
//            "!n3(V1), !c3(V1)\n" +
//            "!n1(V1), !as(V1)\n" +
//            "!n4(V1), !c2(V1)\n" +
//            "!hg(V1), !ni(V1)\n" +
//            "!hg(V1), !npl3(V1)\n" +
//            "!pd(V1), !o3(V2)\n" +
//            "!n3(V1), !so2(V1)\n" +
//            "!pb(V1), !cu(V1)\n" +
//            "!n4(V1), !ge(V1)\n" +
//            "!cd(V1), !n1(V2)\n" +
//            "!c1(V1), !am_bond(V1, V3)\n" +
//            "!se(V1), !p3(V1)\n" +
//            "!i(V1), !n4(V2)\n" +
//            "!car(V1), !ge(V1)\n" +
//            "!cd(V2), !am_bond(V1, V2)\n" +
//            "!n2(V1), !zn(V2)\n" +
//            "!npl3(V1), !br(V1)\n" +
//            "!cr(V1), !pb(V1)\n" +
//            "!n1(V1), !ge(V1)\n" +
//            "!i(V1), !f(V2)\n" +
//            "!am_bond(V1, V2), !pb(V1)\n" +
//            "!car(V1), !c2(V1)\n" +
//            "!sn(V1), !pb(V2)\n" +
//            "!ni(V1), !br(V1)\n" +
//            "!i(V1), !cr(V2)\n" +
//            "!ar_bond(V1, V2), !f(V1)\n" +
//            "!3_bond(V2, V1), !br(V1)\n" +
//            "!car(V1), !as(V1)\n" +
//            "!3_bond(V1, V2), !hg(V1)\n" +
//            "!cu(V1), !nar(V2)\n" +
//            "!pb(V1), !s3(V1)\n" +
//            "!zn(V1), !ge(V1)\n" +
//            "!hg(V1), !oco2(V1)\n" +
//            "!zn(V1), !c2(V1)\n" +
//            "!oco2(V1), !pt(V1)\n" +
//            "!n1(V1), !c2(V1)\n" +
//            "!c1(V1), !n2(V1)\n" +
//            "!so(V1), !i(V1)\n" +
//            "!ar_bond(V1, V2), !nam(V2)\n" +
//            "!pt(V1), !eu(V1)\n" +
//            "!o2(V1), !p3(V1)\n" +
//            "!zn(V1), !as(V1)\n" +
//            "!hg(V1), !eu(V1)\n" +
//            "!oco2(V1), !br(V1)\n" +
//            "!2_bond(V1, V2), !pb(V2)\n" +
//            "!pd(V1), !so(V2)\n" +
//            "!ar_bond(V1, V2), !so2(V1)\n" +
//            "!ge(V1), !car(V2)\n" +
//            "!so(V1), !f(V1)\n" +
//            "!3_bond(V2, V1), !n4(V1)\n" +
//            "!f(V1), !cr(V2)\n" +
//            "!n3(V1), !hg(V2)\n" +
//            "!i(V1), !n1(V2)\n" +
//            "!3_bond(V1, V3), !br(V1)\n" +
//            "!eu(V1), !br(V1)\n" +
//            "!f(V1), !n4(V2)\n" +
//            "!fe(V1), !oco2(V2)\n" +
//            "!ar_bond(V1, V2), !i(V1)\n" +
//            "!pt(V1), !3_bond(V1, V3)\n" +
//            "!n2(V1), !ni(V2)\n" +
//            "!pb(V1), !se(V1)\n" +
//            "!as(V1), !nam(V2)\n" +
//            "!eu(V1), !c2(V1)\n" +
//            "!ni(V1), !n2(V1)\n" +
//            "!pd(V1), !fe(V2)\n" +
//            "!2_bond(V1, V3), !br(V1)\n" +
//            "!cu(V1), !so(V2)\n" +
//            "!zn(V1), !cd(V1)\n" +
//            "!pb(V1), !o2(V1)\n" +
//            "!s3(V1), !au(V2)\n" +
//            "!n1(V1), !cd(V1)\n" +
//            "!ru(V1), !br(V1)\n" +
//            "!pt(V1), !p3(V2)\n" +
//            "!au(V1), !cr(V2)\n" +
//            "!ge(V1), !ru(V2)\n" +
//            "!pd(V1), !o3(V1)\n" +
//            "!nar(V1), !f(V1)\n" +
//            "!so2(V1), !so(V2)\n" +
//            "!s2(V1), !as(V2)\n" +
//            "!ni(V1), !3_bond(V1, V3)\n" +
//            "!oco2(V1), !pb(V2)\n" +
//            "!car(V1), !n2(V1)\n" +
//            "!3_bond(V2, V1), !pt(V1)\n" +
//            "!so2(V1), !au(V2)\n" +
//            "!au(V1), !so(V1)\n" +
//            "!o3(V1), !nar(V1)\n" +
//            "!nam(V1), !o2(V1)\n" +
//            "!cu(V1), !oco2(V2)\n" +
//            "!as(V1), !sn(V1)\n" +
//            "!npl3(V1), !cl(V1)\n" +
//            "!c1(V1), !cu(V2)\n" +
//            "!nam(V1), !se(V1)\n" +
//            "!ar_bond(V1, V2), !npl3(V1)\n" +
//            "!ar_bond(V1, V2), !au(V2)\n" +
//            "!so(V1), !zn(V2)\n" +
//            "!npl3(V1), !fe(V1)\n" +
//            "!fe(V1), !au(V2)\n" +
//            "!car(V1), !npl3(V1)\n" +
//            "!pt(V1), !s2(V2)\n" +
//            "!ge(V1), !nam(V2)\n" +
//            "!pt(V1), !c3(V1)\n" +
//            "!eu(V1), !n2(V1)\n" +
//            "!2_bond(V1, V3), !pt(V1)\n" +
//            "!am_bond(V1, V3), !sn(V1)\n" +
//            "!cr(V1), !oco2(V2)\n" +
//            "!c1(V1), !cr(V2)\n" +
//            "!pd(V1), !f(V2)\n" +
//            "!br(V1), !c3(V1)\n" +
//            "!hg(V1), !c3(V1)\n" +
//            "!s3(V1), !npl3(V1)\n" +
//            "!2_bond(V2, V1), !br(V1)\n" +
//            "!c1(V1), !n4(V2)\n" +
//            "!ar_bond(V1, V2), !so(V2)\n" +
//            "!so(V1), !c1(V1)\n" +
//            "!c2(V1), !sn(V1)\n" +
//            "!o3(V1), !f(V1)\n" +
//            "!n2(V1), !ru(V2)\n" +
//            "!p3(V1), !as(V2)\n" +
//            "!ge(V1), !sn(V1)\n" +
//            "!am_bond(V1, V2), !pt(V1)\n" +
//            "!fe(V1), !so(V2)\n" +
//            "!3_bond(V1, V3), !fe(V1)\n" +
//            "!cu(V1), !pd(V2)\n" +
//            "!f(V1), !fe(V2)\n" +
//            "!pd(V1), !nar(V1)\n" +
//            "!oco2(V1), !p3(V1)\n" +
//            "!au(V1), !3_bond(V1, V2)\n" +
//            "!npl3(V1), !so2(V1)\n" +
//            "!so2(V1), !pd(V2)\n" +
//            "!cd(V1), !cu(V1)\n" +
//            "!am_bond(V1, V2), !br(V1)\n" +
//            "!pt(V1), !hg(V2)\n" +
//            "!au(V1), !o3(V1)\n" +
//            "!3_bond(V1, V3), !cl(V1)\n" +
//            "!n4(V1), !cr(V2)\n" +
//            "!pd(V1), !i(V1)\n" +
//            "!n3(V1), !pt(V1)\n" +
//            "!hg(V1), !hg(V2)\n" +
//            "!br(V1), !hg(V2)\n" +
//            "!hg(V1), !2_bond(V1, V2)\n" +
//            "!hg(V1), !am_bond(V1, V2)\n" +
//            "!pd(V1), !f(V1)\n" +
//            "!n3(V1), !br(V1)\n" +
//            "!as(V1), !o2(V1)\n" +
//            "!n1(V1), !cr(V2)\n" +
//            "!cd(V1), !cr(V1)\n" +
//            "!ar_bond(V1, V2), !o3(V2)\n" +
//            "!n4(V1), !n1(V2)\n" +
//            "!cr(V1), !npl3(V1)\n" +
//            "!n4(V1), !so(V1)\n" +
//            "!as(V1), !se(V1)\n" +
//            "!p3(V1), !s2(V1)\n" +
//            "!hg(V1), !n3(V1)\n" +
//            "!nar(V1), !i(V1)\n" +
//            "!s3(V1), !3_bond(V1, V3)\n" +
//            "!2_bond(V2, V1), !pt(V1)\n" +
//            "!ni(V1), !au(V2)\n" +
//            "!fe(V1), !ge(V1)\n" +
//            "!c2(V1), !cl(V1)\n" +
//            "!zn(V1), !au(V2)\n" +
//            "!br(V1), !se(V1)\n" +
//            "!eu(V1), !pb(V1)\n" +
//            "!hg(V1), !o2(V1)\n" +
//            "!n1(V1), !au(V2)\n" +
//            "!c2(V1), !fe(V1)\n" +
//            "!ar_bond(V1, V2), !c2(V1)\n" +
//            "!zn(V1), !oco2(V2)\n" +
//            "!n4(V1), !so(V2)\n" +
//            "!npl3(V1), !ru(V2)\n" +
//            "!c1(V1), !f(V1)\n" +
//            "!ru(V1), !pb(V2)\n" +
//            "!n4(V1), !i(V1)\n" +
//            "!hg(V1), !se(V1)\n" +
//            "!sn(V1), !cd(V2)\n" +
//            "!au(V1), !cd(V1)\n" +
//            "!cr(V1), !n2(V1)\n" +
//            "!br(V1), !o2(V1)\n" +
//            "!c3(V1), !p3(V1)\n" +
//            "!s2(V1), !hg(V2)\n" +
//            "!pt(V1), !o2(V1)\n" +
//            "!cd(V1), !so2(V1)\n" +
//            "!pt(V1), !se(V1)\n" +
//            "!pd(V1), !n4(V2)\n" +
//            "!ge(V1), !se(V2)\n" +
//            "!3_bond(V1, V3), !sn(V1)\n" +
//            "!pd(V1), !so(V1)\n" +
//            "!pd(V1), !cr(V2)\n" +
//            "!nar(V1), !zn(V2)\n" +
//            "!cd(V1), !zn(V2)\n" +
//            "!eu(V1), !npl3(V1)\n" +
//            "!c1(V1), !i(V2)\n" +
//            "!3_bond(V1, V2), !pb(V1)\n" +
//            "!ar_bond(V2, V1), !ge(V1)\n" +
//            "!am_bond(V2, V2)\n" +
//            "!au(V1), !nar(V1)\n" +
//            "!ni(V1), !as(V1)\n" +
//            "!n2(V1), !cl(V1)\n" +
//            "!s3(V1), !n2(V1)\n" +
//            "!n3(V1), !s2(V1)\n" +
//            "!p3(V1), !hg(V2)\n" +
//            "!n1(V1), !i(V1)\n" +
//            "!n4(V1), !f(V1)\n" +
//            "!am_bond(V1, V2), !s2(V1)\n" +
//            "!oco2(V1), !s2(V1)\n" +
//            "!ru(V1), !nam(V1)\n" +
//            "!c1(V1), !nar(V1)\n" +
//            "!n2(V1), !fe(V1)\n" +
//            "!au(V1), !i(V1)\n" +
//            "!c1(V1), !cd(V1)\n" +
//            "!2_bond(V1, V2), !pb(V1)\n" +
//            "!car(V1), !nam(V1)\n" +
//            "!c1(V1), !pd(V2)\n" +
//            "!pd(V1), !cl(V2)\n" +
//            "!am_bond(V1, V2), !pb(V2)\n" +
//            "!pd(V1), !n1(V2)\n" +
//            "!cu(V1), !au(V2)\n" +
//            "!pb(V1), !sn(V1)\n" +
//            "!n3(V1), !p3(V1)\n" +
//            "!au(V1), !f(V1)\n" +
//            "!ni(V1), !ge(V1)\n" +
//            "!ni(V1), !c2(V1)\n" +
//            "!n4(V1), !nar(V1)\n" +
//            "!am_bond(V1, V2), !p3(V1)\n" +
//            "!n4(V1), !cd(V1)\n" +
//            "!nam(V1), !c3(V1)\n" +
//            "!as(V1), !eu(V1)\n" +
//            "!hg(V1), !ru(V1)\n" +
//            "!f(V1), !zn(V2)\n" +
//            "!ar_bond(V2, V1), !n2(V1)\n" +
//            "!pt(V1), !ru(V1)\n" +
//            "!eu(V1), !3_bond(V1, V3)\n" +
//            "!n3(V1), !pb(V2)\n" +
//            "!i(V1), !zn(V2)\n" +
//            "!c1(V1), !i(V1)\n" +
//            "!ni(V1), !npl3(V1)\n" +
//            "!eu(V1), !ge(V1)\n" +
//            "!pd(V1), !zn(V1)\n" +
//            "!cu(V1), !cl(V2)\n" +
//            "!i(V1), !cu(V1)\n" +
//            "!ar_bond(V1, V2), !oco2(V1)\n" +
//            "!sn(V1), !so(V2)\n" +
//            "!pt(V1), !s2(V1)\n" +
//            "!s3(V1), !cr(V2)\n" +
//            "!ru(V1), !c2(V1)\n" +
//            "!c1(V1), !eu(V2)\n" +
//            "!ge(V1), !br(V2)\n" +
//            "!eu(V1), !pd(V2)\n" +
//            "!ge(V1), !p3(V2)\n" +
//            "!3_bond(V2, V1), !i(V1)\n" +
//            "!br(V1), !s2(V1)\n" +
//            "!pd(V1), !n1(V1)\n" +
//            "!n3(V1), !pb(V1)\n" +
//            "!s3(V1), !nam(V1)\n" +
//            "!au(V1), !oco2(V2)\n" +
//            "!oco2(V1), !n3(V1)\n" +
//            "!hg(V1), !s2(V1)\n" +
//            "!so(V1), !s3(V1)\n" +
//            "!nam(V1), !fe(V1)\n" +
//            "!cd(V1), !eu(V1)\n" +
//            "!fe(V1), !cr(V2)\n" +
//            "!ar_bond(V1, V2), !am_bond(V1, V2)\n" +
//            "!o3(V1), !cu(V1)\n" +
//            "!ar_bond(V2, V1), !am_bond(V1, V2)\n" +
//            "!npl3(V1), !se(V1)\n" +
//            "!so(V1), !fe(V1)\n" +
//            "!se(V1), !au(V2)\n" +
//            "!f(V1), !cu(V1)\n" +
//            "!i(V1), !cr(V1)\n" +
//            "!ge(V1), !s2(V2)\n" +
//            "!au(V1), !c1(V1)\n" +
//            "!as(V1), !c3(V1)\n" +
//            "!pt(V1), !p3(V1)\n" +
//            "!fe(V1), !n4(V2)\n" +
//            "!ru(V1), !n2(V1)\n" +
//            "!so(V1), !cl(V1)\n" +
//            "!nar(V1), !cd(V1)\n" +
//            "!cu(V1), !fe(V2)\n" +
//            "!i(V1), !s3(V1)\n" +
//            "!fe(V1), !n1(V2)\n" +
//            "!hg(V1), !p3(V1)\n" +
//            "!ge(V1), !c3(V1)\n" +
//            "!nam(V1), !cl(V1)\n" +
//            "!pt(V1), !nam(V2)\n" +
//            "!npl3(V1), !o2(V1)\n" +
//            "!ar_bond(V1, V2), !pb(V1)\n" +
//            "!2_bond(V1, V3), !ge(V1)\n" +
//            "1_bond(V2, V1), !cd(V1)\n" +
//            "!cr(V1), !f(V1)\n" +
//            "!car(V1), !cd(V1)\n" +
//            "!cu(V1), !npl3(V2)\n" +
//            "!n1(V1), !f(V1)\n" +
//            "!c1(V1), !f(V2)\n" +
//            "!oco2(V1), !c3(V1)\n" +
//            "!se(V1), !so(V2)\n" +
//            "!c2(V1), !c3(V1)\n" +
//            "!ni(V1), !ar_bond(V1, V3)\n" +
//            "!au(V1), !n4(V1)\n" +
//            "!o3(V1), !s3(V1)\n" +
//            "1_bond(V1, V2), !cr(V2)\n" +
//            "!cd(V1), !ru(V2)\n" +
//            "!br(V1), !p3(V1)\n" +
//            "!o3(V1), !cr(V1)\n" +
//            "!car(V1), !cr(V2)\n" +
//            "!oco2(V1), !so2(V1)\n" +
//            "!zn(V1), !f(V1)\n" +
//            "!3_bond(V1, V2), !cr(V2)\n" +
//            "!ni(V1), !cu(V2)\n" +
//            "!c1(V1), !zn(V2)\n" +
//            "!zn(V1), !i(V1)\n" +
//            "!am_bond(V1, V2), !pt(V2)\n" +
//            "!3_bond(V2, V1), !n2(V1)\n" +
//            "!car(V1), !cu(V2)\n" +
//            "!3_bond(V1, V3), !se(V1)\n" +
//            "!ni(V1), !npl3(V2)\n" +
//            "!ni(V1), !n4(V2)\n" +
//            "!hg(V1), !pb(V2)\n" +
//            "!ar_bond(V1, V2), !as(V1)\n" +
//            "!so(V1), !ni(V1)\n" +
//            "!cd(V1), !sn(V1)\n" +
//            "!ni(V1), !cr(V2)\n" +
//            "!o3(V1), !cl(V1)\n" +
//            "!pt(V1), !pb(V2)\n" +
//            "!cd(V1), !n3(V2)\n" +
//            "!au(V1), !zn(V1)\n" +
//            "!npl3(V1), !sn(V1)\n" +
//            "!pd(V1), !cu(V1)\n" +
//            "!ni(V1), !nam(V1)\n" +
//            "!am_bond(V1, V2), !as(V2)\n" +
//            "!so2(V1), !cd(V2)\n" +
//            "!n1(V1), !au(V1)\n" +
//            "!n3(V1), !as(V1)\n" +
//            "!ge(V1), !hg(V2)\n" +
//            "!c1(V1), !ni(V2)\n" +
//            "!pd(V1), !oco2(V2)\n" +
//            "!n4(V1), !zn(V2)\n" +
//            "!sn(V1), !pd(V2)\n" +
//            "!n3(V1), !ge(V1)\n" +
//            "!o3(V1), !fe(V1)\n" +
//            "!pd(V1), !cr(V1)\n" +
//            "!n2(V1), !c3(V1)\n" +
//            "!n3(V1), !c2(V1)\n" +
//            "!n4(V1), !c1(V1)\n" +
//            "!2_bond(V2, V1), !ge(V1)\n" +
//            "!br(V1), !pb(V2)\n" +
//            "!3_bond(V2, V1), !f(V1)\n" +
//            "!s2(V1), !cd(V2)\n" +
//            "!n2(V1), !so2(V1)\n" +
//            "!am_bond(V1, V2), !br(V2)\n" +
//            "!ge(V1), !o2(V1)\n" +
//            "!c2(V1), !se(V1)\n" +
//            "!cd(V1), !s3(V1)\n" +
//            "!am_bond(V1, V2), !p3(V2)\n" +
//            "!am_bond(V1, V2), !s2(V2)\n" +
//            "!s2(V1), !pb(V2)\n" +
//            "!zn(V1), !so(V1)\n" +
//            "!i(V1), !se(V2)\n" +
//            "!ar_bond(V1, V2), !br(V1)\n" +
//            "!cd(V1), !o2(V2)\n" +
//            "!pd(V1), !au(V1)\n" +
//            "!n1(V1), !so(V1)\n" +
//            "!n2(V1), !sn(V1)\n" +
//            "!f(V1), !se(V2)\n" +
//            "!ge(V1), !se(V1)\n" +
//            "!c3(V1), !cd(V2)\n" +
//            "!oco2(V1), !ru(V1)\n" +
//            "!c2(V1), !o2(V1)\n" +
//            "!o3(V1), !c1(V1)\n" +
//            "!eu(V1), !au(V2)\n" +
//            "!zn(V1), !cr(V2)\n" +
//            "!ru(V1), !pb(V1)\n" +
//            "!am_bond(V1, V2), !ru(V1)\n" +
//            "!nar(V1), !cr(V2)\n" +
//            "!cd(V1), !cl(V1)\n" +
//            "!so(V1), !nar(V1)\n" +
//            "!n4(V1), !o3(V1)\n" +
//            "!ar_bond(V2, V1), !pt(V1)\n" +
//            "!3_bond(V1, V3), !c3(V1)\n" +
//            "!cd(V1), !fe(V1)\n" +
//            "!ar_bond(V1, V2), !hg(V1)\n" +
//            "!pd(V1), !zn(V2)\n" +
//            "!ru(V1), !npl3(V1)\n" +
//            "!ni(V1), !i(V1)\n" +
//            "!ni(V1), !so(V2)\n" +
//            "!3_bond(V1, V3), !so2(V1)\n" +
//            "!eu(V1), !o3(V2)\n" +
//            "!ni(V1), !f(V1)\n" +
//            "!eu(V1), !nam(V1)\n" +
//            "!so(V1), !car(V1)\n" +
//            "!c2(V1), !so2(V1)\n" +
//            "!3_bond(V2, V1), !cd(V1)\n" +
//            "!cu(V1), !cu(V2)\n" +
//            "!pb(V1), !c3(V1)\n" +
//            "!pd(V1), !c1(V1)\n" +
//            "!ge(V1), !so2(V1)\n" +
//            "!eu(V1), !so(V2)\n" +
//            "!n2(V1), !o2(V1)\n" +
//            "!i(V1), !so2(V2)\n" +
//            "!n1(V1), !o3(V1)\n" +
//            "!3_bond(V1, V2), !nam(V1)\n" +
//            "!cu(V1), !i(V2)\n" +
//            "!pb(V1), !so2(V1)\n" +
//            "!i(V1), !cl(V1)\n" +
//            "!as(V1), !so2(V1)\n" +
//            "!so(V1), !cu(V1)\n" +
//            "!cu(V1), !f(V2)\n" +
//            "!cd(V1), !se(V2)\n" +
//            "!p3(V1), !pb(V2)\n" +
//            "!cu(V1), !cr(V2)\n" +
//            "!am_bond(V1, V2), !so2(V1)\n" +
//            "!am_bond(V1, V2), !c3(V1)\n" +
//            "!n2(V1), !se(V1)\n" +
//            "!am_bond(V1, V2), !n3(V1)\n" +
//            "!f(V1), !s3(V1)\n" +
//            "!i(V1), !fe(V1)\n" +
//            "!cu(V1), !n4(V2)\n" +
//            "!s3(V1), !i(V2)\n" +
//            "!ru(V1), !3_bond(V1, V3)\n" +
//            "!cu(V1), !n1(V2)\n" +
//            "!ni(V1), !pd(V2)\n" +
//            "!as(V1), !ru(V1)\n" +
//            "!o3(V1), !car(V1)\n" +
//            "!2_bond(V1, V2), !au(V2)\n" +
//            "!sn(V1), !au(V2)\n" +
//            "!f(V1), !cl(V1)\n" +
//            "!so(V1), !oco2(V2)\n" +
//            "!ar_bond(V1, V3), !sn(V1)\n" +
//            "!hg(V1), !as(V2)\n" +
//            "!cr(V1), !cr(V2)\n" +
//            "!nam(V1), !sn(V1)\n" +
//            "!br(V1), !as(V2)\n" +
//            "!hg(V2), !am_bond(V1, V2)\n" +
//            "!ru(V1), !ge(V1)\n" +
//            "!cd(V1), !ni(V1)\n" +
//            "!f(V1), !fe(V1)\n" +
//            "!pt(V1), !pt(V2)\n" +
//            "!cd(V2), !ar_bond(V1, V2)\n" +
//            "!so(V1), !cr(V1)\n" +
//            "!pt(V1), !as(V2)\n" +
//            "!zn(V1), !o3(V1)\n" +
//            "!pd(V1), !n4(V1)\n" +
//            "!eu(V1), !i(V2)\n" +
//            "!ge(V1), !c2(V2)\n" +
//            "!f(V1), !3_bond(V1, V3)\n" +
//            "!oco2(V2), !am_bond(V1, V2)\n" +
//            "!nam(V1), !pb(V2)\n" +
//            "!c1(V1), !cr(V1)\n" +
//            "!2_bond(V2, V2)\n" +
//            "!au(V1), !cl(V1)\n" +
//            "!eu(V1), !so2(V2)\n" +
//            "!f(V1), !ru(V2)\n" +
//            "!oco2(V1), !o2(V1)\n" +
//            "!oco2(V1), !se(V1)\n" +
//            "!as(V1), !s2(V1)\n" +
//            "!c1(V1), !so2(V1)\n" +
//            "!3_bond(V2, V1), !2_bond(V1, V2)\n" +
//            "!3_bond(V1, V2), !2_bond(V1, V2)\n" +
//            "!n3(V1), !au(V2)\n" +
//            "!au(V1), !fe(V1)\n" +
//            "!c2(V1), !s2(V1)\n" +
//            "!c1(V1), !s3(V1)\n" +
//            "!ge(V1), !s2(V1)\n" +
//            "!n4(V1), !so2(V1)\n" +
//            "!n4(V1), !cu(V1)\n" +
//            "!i(V1), !sn(V1)\n" +
//            "!o3(V1), !ru(V2)\n" +
//            "!s3(V1), !zn(V2)\n" +
//            "!o2(V1), !cr(V2)\n" +
//            "!as(V1), !p3(V1)\n" +
//            "!n4(V1), !cr(V1)\n" +
//            "!n1(V1), !cu(V1)\n" +
//            "!f(V1), !sn(V1)\n" +
//            "!so(V1), !o2(V1)\n" +
//            "!o3(V1), !sn(V1)\n" +
//            "!cd(V1), !ru(V1)\n" +
//            "!pd(V1), !eu(V1)\n" +
//            "!am_bond(V1, V2), !se(V1)\n" +
//            "!se(V1), !cr(V2)\n" +
//            "!3_bond(V2, V1), !ru(V1)\n" +
//            "!i(V1), !3_bond(V1, V3)\n" +
//            "!npl3(V1), !cr(V2)\n" +
//            "!se(V1), !n1(V2)\n" +
//            "!ge(V1), !p3(V1)\n" +
//            "!n1(V1), !so2(V1)\n" +
//            "!ni(V1), !eu(V2)\n" +
//            "!sn(V1), !fe(V2)\n" +
//            "!ar_bond(V1, V2), !pb(V2)\n" +
//            "!c1(V1), !cl(V1)\n" +
//            "!so(V1), !se(V1)\n" +
//            "!se(V1), !n4(V2)\n" +
//            "!am_bond(V1, V2), !o2(V1)\n" +
//            "!pd(V1), !car(V1)\n" +
//            "!c1(V1), !fe(V1)\n" +
//            "!nar(V1), !eu(V1)\n" +
//            "!so(V1), !npl3(V1)\n" +
//            "!au(V1), !so2(V1)\n" +
//            "!fe(V1), !zn(V2)\n" +
//            "!n2(V1), !pt(V2)\n" +
//            "!cd(V1), !br(V2)\n" +
//            "!oco2(V1), !sn(V1)\n" +
//            "!sn(V1), !so2(V2)\n" +
//            "!pt(V1), !cd(V2)\n" +
//            "!am_bond(V1, V2), !npl3(V1)\n" +
//            "1_bond(V1, V2), !ru(V2)\n" +
//            "!n1(V1), !s3(V1)\n" +
//            "!zn(V1), !cr(V1)\n" +
//            "!n2(V1), !s2(V1)\n" +
//            "!c2(V1), !p3(V1)\n" +
//            "!n1(V1), !cr(V1)\n" +
//            "!zn(V1), !so2(V1)\n" +
//            "!ge(V1), !ge(V2)\n" +
//            "!ni(V1), !i(V2)\n" +
//            "!car(V1), !nar(V1)\n" +
//            "!n4(V1), !s3(V1)\n" +
//            "!zn(V1), !cu(V1)\n" +
//            "!pd(V1), !ru(V2)\n" +
//            "!npl3(V1), !i(V2)\n" +
//            "!au(V1), !ni(V1)\n" +
//            "!n4(V1), !cl(V1)\n" +
//            "!as(V1), !pb(V2)\n" +
//            "!i(V1), !eu(V1)\n" +
//            "!c3(V1), !pd(V2)\n" +
//            "!nar(V1), !ru(V2)\n" +
//            "!so(V1), !3_bond(V1, V3)\n" +
//            "!2_bond(V2, V1), !cd(V1)\n" +
//            "!au(V1), !car(V1)\n" +
//            "!eu(V1), !eu(V2)\n" +
//            "!c1(V1), !se(V2)\n" +
//            "!cd(V1), !c3(V1)\n" +
//            "!i(V1), !npl3(V1)\n" +
//            "!n4(V1), !fe(V1)\n" +
//            "!pt(V1), !pb(V1)\n" +
//            "!o3(V1), !se(V1)\n" +
//            "!ni(V1), !zn(V2)\n" +
//            "!f(V1), !eu(V1)\n" +
//            "!o3(V1), !o2(V1)\n" +
//            "!n2(V1), !p3(V1)\n" +
//            "!pb(V1), !br(V1)\n" +
//            "!n1(V1), !fe(V1)\n" +
//            "!pd(V1), !n3(V2)\n" +
//            "!nar(V1), !sn(V1)\n" +
//            "!zn(V1), !s3(V1)\n" +
//            "!cd(V1), !oco2(V2)\n" +
//            "!n1(V1), !cl(V1)\n" +
//            "!oco2(V2), !3_bond(V1, V2)\n" +
//            "!ni(V1), !f(V2)\n" +
//            "!car(V1), !f(V1)\n" +
//            "!hg(V1), !pb(V1)\n" +
//            "!pd(V1), !sn(V1)\n" +
//            "!i(V1), !ru(V2)\n" +
//            "!ge(V1), !pb(V2)\n" +
//            "!f(V1), !npl3(V1)\n" +
//            "!am_bond(V1, V2), !ru(V2)\n" +
//            "!oco2(V1), !eu(V1)\n" +
//            "!hg(V1), !nam(V1)\n" +
//            "!car(V1), !i(V1)\n" +
//            "!fe(V1), !fe(V2)\n" +
//            "!3_bond(V2, V1), !npl3(V1)\n" +
//            "!n4(V1), !car(V1)\n" +
//            "!ar_bond(V1, V2), !as(V2)\n" +
//            "!2_bond(V2, V1), !i(V1)\n" +
//            "!pt(V1), !nam(V1)\n" +
//            "!ni(V1), !cl(V2)\n" +
//            "!eu(V1), !n4(V2)\n" +
//            "!pd(V1), !o2(V2)\n" +
//            "!ni(V1), !n1(V2)\n" +
//            "!n1(V1), !zn(V2)\n" +
//            "!pd(V1), !s3(V1)\n" +
//            "!nam(V1), !br(V1)\n" +
//            "!car(V1), !ni(V2)\n" +
//            "!p3(V1), !cd(V2)\n" +
//            "!eu(V1), !cr(V2)\n" +
//            "!n1(V1), !c1(V1)\n" +
//            "!am_bond(V1, V2), !eu(V1)\n" +
//            "!au(V1), !nam(V2)\n" +
//            "!cu(V1), !eu(V2)\n" +
//            "!zn(V1), !car(V1)\n" +
//            "!3_bond(V2, V1), !n3(V1)\n" +
//            "!c1(V1), !car(V1)\n" +
//            "!ar_bond(V2, V2)\n" +
//            "!n2(V1), !hg(V2)\n" +
//            "!nar(V1), !o2(V1)\n" +
//            "!ru(V1), !au(V2)\n" +
//            "!pd(V1), !cl(V1)\n" +
//            "!so(V1), !eu(V1)\n" +
//            "!nar(V1), !se(V1)\n" +
//            "!2_bond(V1, V2), !n1(V2)\n" +
//            "!pb(V1), !s2(V1)\n" +
//            "!zn(V1), !zn(V2)\n" +
//            "!ge(V1), !nar(V2)\n" +
//            "!n1(V1), !n4(V1)\n" +
//            "!n3(V1), !n2(V1)\n" +
//            "!ar_bond(V1, V2), !c2(V2)\n" +
//            "!pd(V1), !npl3(V1)\n" +
//            "!eu(V1), !n1(V2)\n" +
//            "!zn(V1), !c1(V1)\n" +
//            "!cd(V1), !o2(V1)\n" +
//            "!pd(V1), !fe(V1)\n" +
//            "!cd(V1), !se(V1)\n" +
//            "!eu(V1), !cl(V2)\n" +
//            "!ni(V1), !fe(V2)\n" +
//            "!au(V1), !cu(V1)\n" +
//            "!sn(V1), !i(V2)\n" +
//            "!car(V1), !zn(V2)\n" +
//            "!sn(V1), !cu(V2)\n" +
//            "!ar_bond(V1, V2), !p3(V1)\n" +
//            "!cu(V1), !zn(V2)\n" +
//            "!pd(V1), !se(V2)\n" +
//            "!so(V1), !n3(V2)\n" +
//            "!npl3(V1), !fe(V2)\n" +
//            "!sn(V1), !cr(V2)\n" +
//            "!oco2(V1), !npl3(V1)\n" +
//            "!2_bond(V1, V2), !cr(V2)\n" +
//            "!i(V1), !se(V1)\n" +
//            "!ni(V1), !so2(V2)\n" +
//            "!o3(V1), !ni(V1)\n" +
//            "!so(V1), !ru(V2)\n" +
//            "!pd(V1), !3_bond(V1, V3)\n" +
//            "!3_bond(V2, V1), !eu(V1)\n" +
//            "!zn(V1), !n4(V1)\n" +
//            "!pb(V1), !p3(V1)\n" +
//            "!au(V1), !cr(V1)\n" +
//            "!2_bond(V1, V3), !cd(V1)\n" +
//            "!2_bond(V2, V1), !f(V1)\n" +
//            "!ge(V1), !pt(V2)\n" +
//            "!2_bond(V2, V1), !am_bond(V1, V2)\n" +
//            "!2_bond(V1, V2), !am_bond(V1, V2)\n" +
//            "!am_bond(V1, V2), !n3(V2)\n" +
//            "!au(V1), !s3(V1)\n" +
//            "!o3(V1), !eu(V1)\n" +
//            "!pt(V1), !br(V1)\n" +
//            "!hg(V1), !pt(V1)\n" +
//            "!pd(V1), !ni(V1)\n" +
//            "!n4(V1), !nam(V2)\n" +
//            "!so2(V1), !zn(V2)\n" +
//            "!i(V1), !o2(V1)\n" +
//            "!hg(V1), !br(V1)\n" +
//            "!n1(V1), !car(V1)\n" +
//            "!c1(V1), !cu(V1)\n" +
//            "!pb(V1), !pb(V2)\n" +
//            "!am_bond(V2, V1), !sn(V1)\n" +
//            "!2_bond(V1, V3), !i(V1)\n" +
//            "!f(V1), !o2(V1)\n" +
//            "!sn(V1), !n4(V2)\n" +
//            "!ge(V1), !as(V2)\n" +
//            "!o3(V1), !npl3(V1)\n" +
//            "!oco2(V1), !ni(V1)\n" +
//            "!f(V1), !se(V1)\n" +
//            "!n1(V1), !zn(V1)\n" +
//            "!so(V1), !sn(V1)\n" +
//            "!eu(V1), !fe(V2)\n" +
//            "!oco2(V1), !cl(V1)\n" +
//            "!p3(V1), !so(V2)\n" +
//            "!c1(V1), !npl3(V1)\n" +
//            "!car(V1), !cu(V1)\n" +
//            "!s3(V1), !fe(V1)\n" +
//            "!ar_bond(V1, V2), !npl3(V2)\n" +
//            "!so(V1), !n3(V1)\n" +
//            "!n4(V1), !sn(V1)\n" +
//            "!se(V2), !am_bond(V1, V2)\n" +
//            "!ar_bond(V1, V2), !nam(V1)\n" +
//            "!sn(V1), !ni(V2)\n" +
//            "!i(V1), !c3(V1)\n" +
//            "!am_bond(V1, V2), !fe(V1)\n" +
//            "!ni(V1), !cu(V1)\n" +
//            "!zn(V1), !eu(V1)\n" +
//            "!2_bond(V1, V3), !f(V1)\n" +
//            "!oco2(V1), !fe(V1)\n" +
//            "!n4(V1), !3_bond(V1, V3)\n" +
//            "!am_bond(V1, V2), !cl(V1)\n" +
//            "!so(V1), !hg(V2)\n" +
//            "!cd(V1), !as(V2)\n" +
//            "!au(V1), !se(V1)\n" +
//            "!fe(V1), !cl(V1)\n" +
//            "!n1(V1), !nar(V1)\n" +
//            "!au(V1), !o2(V1)\n" +
//            "!so2(V1), !cr(V2)\n" +
//            "!npl3(V1), !zn(V2)\n" +
//            "!am_bond(V2, V1), !car(V1)\n" +
//            "!hg(V1), !n2(V1)\n" +
//            "!n2(V1), !br(V1)\n" +
//            "!o3(V1), !c3(V1)\n" +
//            "!n1(V1), !sn(V1)\n" +
//            "!o3(V1), !2_bond(V1, V3)\n" +
//            "!f(V1), !c3(V1)\n" +
//            "!hg(V2), !ar_bond(V1, V2)\n" +
//            "!cd(V1), !c2(V2)\n" +
//            "!s2(V1), !pd(V2)\n" +
//            "!o3(V1), !so2(V1)\n" +
//            "!c1(V1), !se(V1)\n" +
//            "!cd(V1), !s2(V1)\n" +
//            "!pd(V1), !ru(V1)\n" +
//            "!se(V1), !zn(V2)\n" +
//            "!pd(V1), !br(V2)\n" +
//            "!n3(V1), !nam(V1)\n" +
//            "!3_bond(V2, V1), !car(V1)\n" +
//            "!ar_bond(V1, V2), !n3(V1)\n" +
//            "!pt(V1), !n2(V1)\n" +
//            "!i(V1), !hg(V2)\n" +
//            "!ni(V1), !cr(V1)\n" +
//            "!s3(V1), !se(V2)\n" +
//            "!zn(V1), !nar(V1)\n" +
//            "!fe(V1), !se(V2)\n" +
//            "!car(V1), !cl(V1)\n" +
//            "!oco2(V1), !cu(V1)\n" +
//            "!3_bond(V2, V1), !fe(V1)\n" +
//            "!ge(V1), !cd(V2)\n" +
//            "!3_bond(V2, V1), !s2(V1)\n" +
//            "!c1(V1), !o2(V1)\n" +
//            "!eu(V1), !cu(V1)\n" +
//            "!f(V1), !so2(V1)\n" +
//            "!au(V1), !npl3(V1)\n" +
//            "!3_bond(V2, V1), !cl(V1)\n" +
//            "!pd(V1), !2_bond(V2, V1)\n" +
//            "!zn(V1), !sn(V1)\n" +
//            "!ni(V1), !o2(V2)\n" +
//            "!cd(V1), !nam(V2)\n" +
//            "!o3(V1), !hg(V2)\n" +
//            "!i(V1), !ru(V1)\n" +
//            "!ni(V1), !s3(V1)\n" +
//            "!oco2(V1), !s3(V1)\n" +
//            "!cu(V1), !ru(V2)\n" +
//            "!ni(V1), !fe(V1)\n" +
//            "!2_bond(V2, V1), !o3(V1)\n" +
//            "!car(V1), !s3(V1)\n" +
//            "!ru(V1), !f(V1)\n" +
//            "!pd(V1), !so2(V1)\n" +
//            "!cr(V1), !eu(V1)\n" +
//            "!n1(V1), !se(V1)\n" +
//            "!i(V1), !so2(V1)\n" +
//            "!i(V1), !br(V2)\n" +
//            "!nar(V1), !cr(V1)\n" +
//            "!oco2(V1), !cr(V1)\n" +
//            "!ni(V1), !cl(V1)\n" +
//            "!pd(V1), !c3(V1)\n" +
//            "!cd(V1), !p3(V1)\n" +
//            "!as(V1), !pb(V1)\n" +
//            "!3_bond(V2, V1), !p3(V1)\n" +
//            "!n4(V1), !se(V1)\n" +
//            "!p3(V1), !pd(V2)\n" +
//            "!nar(V1), !cu(V1)\n" +
//            "!pb(V1), !c2(V1)\n" +
//            "!o3(V1), !n3(V1)\n" +
//            "!n4(V1), !o2(V1)\n" +
//            "!eu(V1), !s3(V1)\n" +
//            "!pb(V1), !ge(V1)\n" +
//            "!3_bond(V1, V2), !cr(V1)\n" +
//            "!am_bond(V1, V2), !ni(V1)\n" +
//            "!car(V1), !cr(V1)\n" +
//            "1_bond(V1, V2), !cr(V1)\n" +
//            "!c1(V1), !ni(V1)\n" +
//            "!zn(V1), !fe(V1)\n" +
//            "!au(V1), !eu(V1)\n" +
//            "!ni(V1), !ni(V2)\n" +
//            "!eu(V1), !s3(V2)\n" +
//            "!cd(V1), !hg(V2)\n" +
//            "!ar_bond(V1, V2), !ru(V1)\n" +
//            "!nar(V1), !s3(V1)\n" +
//            "!eu(V1), !zn(V2)\n" +
//            "!3_bond(V2, V1), !cu(V1)\n" +
//            "!zn(V1), !cl(V1)\n" +
//            "!ru(V1), !cr(V2)\n" +
//            "!zn(V1), !oco2(V1)\n" +
//            "!npl3(V1), !cu(V1)\n" +
//            "!cr(V1), !cu(V1)\n" +
//            "!ge(V1), !oco2(V2)\n" +
//            "!cu(V1), !3_bond(V1, V3)\n" +
//            "!n2(V1), !pb(V2)\n" +
//            "!ru(V1), !n1(V2)\n" +
//            "!am_bond(V1, V2), !cu(V1)\n" +
//            "!3_bond(V1, V2), !pb(V2)\n" +
//            "!sn(V1), !eu(V2)\n" +
//            "!pd(V1), !o2(V1)\n" +
//            "!nar(V1), !fe(V1)\n" +
//            "!pd(V1), !se(V1)\n" +
//            "!cu(V1), !o2(V2)\n" +
//            "!ar_bond(V1, V2), !br(V2)\n" +
//            "!n3(V1), !cd(V1)\n" +
//            "!nar(V1), !npl3(V1)\n" +
//            "!s2(V1), !au(V2)\n" +
//            "!so(V1), !ru(V1)\n" +
//            "!car(V1), !fe(V1)\n" +
//            "!c1(V1), !eu(V1)\n" +
//            "!c1(V1), !ar_bond(V1, V3)\n" +
//            "!nar(V1), !cl(V1)\n" +
//            "!n4(V1), !ni(V1)\n" +
//            "!s3(V1), !cu(V1)\n" +
//            "!pt(V1), !nar(V2)\n" +
//            "!am_bond(V1, V2), !c3(V2)\n" +
//            "!au(V1), !2_bond(V1, V2)\n" +
//            "!car(V1), !eu(V1)\n" +
//            "!zn(V1), !ni(V1)\n" +
//            "!n4(V1), !oco2(V1)\n" +
//            "!zn(V1), !npl3(V1)\n" +
//            "!n3(V1), !i(V1)\n" +
//            "!cr(V1), !s3(V1)\n" +
//            "!au(V1), !sn(V1)\n" +
//            "!am_bond(V1, V2), !o2(V2)\n" +
//            "!so2(V1), !fe(V2)\n" +
//            "!am_bond(V1, V2), !cr(V1)\n" +
//            "!cu(V1), !so2(V2)\n" +
//            "!oco2(V1), !car(V1)\n" +
//            "!n1(V1), !npl3(V1)\n" +
//            "!nar(V1), !3_bond(V1, V3)\n" +
//            "!am_bond(V1, V2), !s3(V1)\n" +
//            "!cu(V1), !cl(V1)\n" +
//            "!cu(V1), !fe(V1)\n" +
//            "!hg(V1), !as(V1)\n" +
//            "!pt(V1), !as(V1)\n" +
//            "!n4(V1), !eu(V1)\n" +
//            "!n1(V1), !ni(V1)\n" +
//            "!f(V1), !hg(V2)\n" +
//            "!nam(V1), !s2(V1)\n" +
//            "!2_bond(V1, V2), !zn(V2)\n" +
//            "!car(V1), !3_bond(V1, V3)\n" +
//            "!c1(V1), !ru(V2)\n" +
//            "!c3(V1), !cr(V2)\n" +
//            "!as(V1), !br(V1)\n" +
//            "!so2(V1), !n4(V2)\n" +
//            "!cr(V1), !cl(V1)\n" +
//            "!n4(V1), !npl3(V1)\n" +
//            "!pd(V1), !2_bond(V1, V3)\n" +
//            "!pt(V1), !ge(V1)\n" +
//            "!ar_bond(V1, V2), !p3(V2)\n" +
//            "!so(V1), !so2(V1)\n" +
//            "!ge(V1), !n2(V2)\n" +
//            "!hg(V1), !c2(V1)\n" +
//            "!br(V1), !ge(V1)\n" +
//            "!cr(V1), !fe(V1)\n" +
//            "!nar(V1), !ni(V1)\n" +
//            "!n4(V1), !ru(V2)\n" +
//            "!so(V1), !c3(V1)\n" +
//            "!n3(V1), !f(V1)\n" +
//            "!hg(V1), !ge(V1)\n" +
//            "!nam(V1), !p3(V1)\n" +
//            "!cu(V1), !se(V2)\n" +
//            "!1_bond(V2, V1), !s2(V1)\n" +
//            "!c2(V1), !br(V1)\n" +
//            "!s3(V1), !cl(V1)\n" +
//            "!c1(V1), !sn(V1)\n" +
//            "!car(V1), !ni(V1)\n" +
//            "!fe(V1), !o2(V2)\n" +
//            "!sn(V1), !zn(V2)\n" +
//            "!pt(V1), !c2(V1)\n" +
//            "!n1(V1), !eu(V1)\n" +
//            "!ar_bond(V1, V2), !c3(V1)\n" +
//            "!s2(V1), !so(V2)\n" +
//            "!n1(V1), !oco2(V1)\n" +
//            "!o3(V1), !ru(V1)\n" +
//            "!2_bond(V2, V1), !cu(V1)\n" +
//            "!so(V1), !pb(V2)\n" +
//            "!ar_bond(V1, V2), !se(V1)\n" +
//            "!am_bond(V1, V2), !nar(V2)\n" +
//            "!car(V1), !sn(V1)\n" +
//            "!au(V1), !oco2(V1)\n" +
//            "!hg(V1), !cd(V1)\n" +
//            "!au(V1), !n3(V1)\n" +
//            "!f(V1), !s2(V1)\n" +
//            "!sn(V1), !se(V2)\n" +
//            "!nar(V1), !as(V2)\n" +
//            "!as(V1), !n2(V1)\n" +
//            "!n4(V1), !c3(V1)\n" +
//            "!se(V1), !cl(V1)\n" +
//            "!ni(V1), !ru(V2)\n" +
//            "!pd(V1), !as(V2)\n" +
//            "!s3(V1), !se(V1)\n" +
//            "!am_bond(V1, V2), !ni(V2)\n" +
//            "!cd(V1), !br(V1)\n" +
//            "!cd(V1), !pt(V1)\n" +
//            "!oco2(V2), !ar_bond(V1, V2)\n" +
//            "!pt(V1), !pd(V2)\n" +
//            "!2_bond(V1, V2), !cr(V1)\n" +
//            "!zn(V1), !ru(V1)\n" +
//            "!s2(V1), !fe(V2)\n" +
//            "!i(V1), !p3(V1)\n" +
//            "!o3(V1), !s2(V1)\n" +
//            "!s3(V1), !o2(V1)\n" +
//            "!fe(V1), !o2(V1)\n" +
//            "!n2(V1), !ge(V1)\n" +
//            "!eu(V1), !nam(V2)\n" +
//            "!f(V1), !p3(V1)\n" +
//            "!car(V1), !ru(V2)\n" +
//            "!npl3(V1), !nam(V1)\n" +
//            "!zn(V1), !3_bond(V1, V2)\n" +
//            "!fe(V1), !se(V1)\n" +
//            "!o2(V1), !cl(V1)\n" +
//            "!s2(V1), !i(V2)\n" +
//            "!am_bond(V2, V1), !c1(V1)\n" +
//            "!ni(V1), !n3(V2)\n" +
//            "!1_bond(V1, V3), !s2(V1)\n" +
//            "!p3(V1), !fe(V2)\n" +
//            "!n1(V1), !c3(V1)\n" +
//            "!c1(V1), !n3(V1)\n" +
//            "!c2(V1), !n2(V1)\n" +
//            "!am_bond(V1, V2), !zn(V2)\n" +
//            "!ni(V1), !sn(V1)\n" +
//            "!p3(V1), !so2(V2)\n" +
//            "!eu(V1), !ru(V2)\n" +
//            "!o3(V1), !p3(V1)\n" +
//            "!oco2(V1), !c1(V1)\n" +
//            "!n1(V1), !am_bond(V1, V2)\n" +
//            "!ru(V1), !cu(V1)\n" +
//            "!eu(V1), !n3(V2)\n" +
//            "!i(V1), !as(V2)\n" +
//            "!n4(V1), !hg(V2)\n" +
//            "!pb(V1), !npl3(V1)\n" +
//            "!ar_bond(V1, V2), !1_bond(V1, V2)\n" +
//            "!ar_bond(V1, V2), !1_bond(V2, V1)\n" +
//            "!zn(V1), !am_bond(V1, V2)\n" +
//            "!ru(V1), !so2(V1)\n" +
//            "!pd(V1), !s2(V1)\n" +
//            "!zn(V1), !c3(V1)\n" +
//            "!se(V1), !se(V2)\n" +
//            "!eu(V1), !sn(V1)\n" +
//            "!n4(V1), !n3(V1)\n" +
//            "!nar(V1), !s2(V1)\n" +
//            "!f(V1), !as(V2)\n" +
//            "!n2(V1), !am_bond(V1, V3)\n" +
//            "!ru(V1), !o2(V2)\n" +
//            "!cu(V1), !br(V2)\n" +
//            "!sn(V1), !ru(V2)\n" +
//            "!npl3(V1), !cd(V2)\n" +
//            "!n1(V1), !n3(V1)\n" +
//            "!ni(V1), !o2(V1)\n" +
//            "!cu(V1), !p3(V2)\n" +
//            "!i(V1), !s2(V1)\n" +
//            "!cd(V1), !cd(V2)\n" +
//            "!n1(V1), !2_bond(V1, V2)\n" +
//            "!n4(V1), !am_bond(V1, V2)\n" +
//            "!ru(V1), !s3(V1)\n" +
//            "!cr(V1), !ru(V1)\n" +
//            "!zn(V1), !2_bond(V1, V2)\n" +
//            "!o3(V1), !pb(V2)\n" +
//            "!pd(V1), !p3(V1)\n" +
//            "!cu(V1), !s2(V2)\n" +
//            "!au(V1), !as(V2)\n" +
//            "!ni(V1), !se(V1)\n" +
//            "!nar(V1), !p3(V1)\n" +
//            "!pt(V1), !cu(V2)\n" +
//            "!cu(V1), !sn(V1)\n" +
//            "!pd(V1), !hg(V2)\n" +
//            "!cu(V1), !n3(V2)\n" +
//            "!npl3(V1), !n2(V1)\n" +
//            "!nar(V1), !pb(V2)\n" +
//            "!pt(V1), !au(V2)\n" +
//            "!au(V1), !ru(V1)\n" +
//            "!n2(V1), !cd(V2)\n" +
//            "!zn(V1), !se(V1)\n" +
//            "!1_bond(V2, V2)\n" +
//            "!ni(V1), !se(V2)\n" +
//            "!br(V1), !au(V2)\n" +
//            "!zn(V1), !o2(V1)\n" +
//            "!s3(V1), !ru(V2)\n" +
//            "!n1(V1), !o2(V1)\n" +
//            "!3_bond(V1, V2), !o2(V2)\n" +
//            "!hg(V1), !au(V2)\n" +
//            "!sn(V1), !so2(V1)\n" +
//            "!cd(V1), !pb(V2)\n" +
//            "!eu(V1), !cl(V1)\n" +
//            "!3_bond(V2, V1), !s3(V1)\n" +
//            "!so(V1), !as(V2)\n" +
//            "!2_bond(V1, V3), !fe(V1)\n" +
//            "!2_bond(V2, V1), !ni(V1)\n" +
//            "!ge(V1), !am_bond(V1, V3)\n" +
//            "!eu(V1), !fe(V1)\n" +
//            "!npl3(V1), !3_bond(V1, V3)\n" +
//            "!hg(V1), !oco2(V2)\n" +
//            "!3_bond(V2, V2)\n" +
//            "!s2(V1), !n4(V2)\n" +
//            "!cr(V1), !sn(V1)\n" +
//            "!pb(V1), !n2(V1)\n" +
//            "!3_bond(V1, V2), !ar_bond(V1, V2)\n" +
//            "!ar_bond(V1, V2), !3_bond(V2, V1)\n" +
//            "!s3(V1), !sn(V1)\n" +
//            "!so(V1), !s2(V1)\n" +
//            "!pt(V1), !so(V2)\n" +
//            "!pd(V1), !n3(V1)\n" +
//            "!pd(V1), !oco2(V1)\n" +
//            "!br(V1), !so(V2)\n" +
//            "!fe(V1), !ru(V2)\n" +
//            "!pt(V1), !i(V1)\n" +
//            "!cd(V1), !nam(V1)\n" +
//            "!s2(V1), !cr(V2)\n" +
//            "!c1(V1), !ru(V1)\n" +
//            "!ru(V1), !zn(V2)\n" +
//            "!hg(V1), !i(V1)\n" +
//            "!pd(V1), !am_bond(V1, V2)\n" +
//            "!car(V1), !o2(V1)\n" +
//            "!ar_bond(V1, V2), !eu(V1)\n" +
//            "!3_bond(V1, V3), !n2(V1)\n" +
//            "!car(V1), !se(V1)\n" +
//            "!ar_bond(V1, V2), !2_bond(V1, V2)\n" +
//            "!ar_bond(V1, V2), !2_bond(V2, V1)\n" +
//            "!au(V1), !c3(V1)\n" +
//            "!am_bond(V1, V2), !eu(V2)\n" +
//            "!car(V1), !ru(V1)\n" +
//            "!i(V1), !pb(V2)\n" +
//            "!npl3(V1), !ge(V1)\n" +
//            "!n4(V1), !ru(V1)\n" +
//            "!eu(V1), !se(V2)\n" +
//            "!c1(V1), !br(V2)\n" +
//            "!n1(V1), !1_bond(V1, V2)\n" +
//            "!3_bond(V1, V2), !c2(V2)\n" +
//            "!ar_bond(V2, V1), !sn(V1)\n" +
//            "!npl3(V1), !c2(V1)\n" +
//            "!2_bond(V2, V1), !fe(V1)\n" +
//            "!f(V1), !pb(V2)\n" +
//            "!p3(V1), !cr(V2)\n" +
//            "!cu(V1), !o2(V1)\n" +
//            "!fe(V1), !sn(V1)\n" +
//            "!fe(V1), !n3(V2)\n" +
//            "!as(V1), !ge(V1)\n" +
//            "!c1(V1), !s2(V2)\n" +
//            "!cu(V1), !se(V1)\n" +
//            "!sn(V1), !cl(V1)\n" +
//            "!3_bond(V1, V2), !as(V2)\n" +
//            "!as(V1), !c2(V1)\n" +
//            "!se(V1), !so2(V1)\n" +
//            "!s2(V1), !n1(V2)\n" +
//            "!am_bond(V1, V2), !s3(V2)\n" +
//            "!o2(V1), !so2(V1)\n" +
//            "!cr(V1), !se(V1)\n" +
//            "!au(V1), !am_bond(V1, V2)\n" +
//            "!p3(V1), !n1(V2)\n" +
//            "!n1(V1), !ru(V1)\n" +
//            "!ni(V1), !eu(V1)\n" +
//            "!ar_bond(V1, V2), !ru(V2)\n" +
//            "!c2(V1), !ge(V1)\n" +
//            "!3_bond(V1, V3), !ge(V1)\n" +
//            "!2_bond(V1, V3), !cu(V1)\n" +
//            "!c1(V1), !c3(V1)\n" +
//            "!so(V1), !p3(V1)\n" +
//            "!cr(V1), !o2(V1)\n" +
//            "!ar_bond(V1, V2), !n3(V2)\n" +
//            "!o3(V1), !oco2(V1)\n" +
//            "!as(V1), !npl3(V1)\n" +
//            "!so2(V1), !se(V2)\n" +
//            "!cd(V1), !as(V1)\n" +
//            "!ge(V1), !pd(V2)\n" +
//            "!3_bond(V2, V1), !ni(V1)\n" +
//            "!2_bond(V1, V2), !n3(V2)\n" +
//            "!n3(V1), !cl(V1)\n" +
//            "!pt(V1), !f(V2)\n" +
//            "!car(V1), !c3(V1)\n" +
//            "!pd(V1), !pt(V1)\n" +
//            "!am_bond(V1, V2), !n1(V2)\n" +
//            "!n4(V1), !p3(V1)\n" +
//            "!so(V1), !pb(V1)\n" +
//            "!ar_bond(V1, V2), !fe(V1)\n" +
//            "!fe(V1), !hg(V2)\n" +
//            "!am_bond(V1, V2), !cr(V2)\n" +
//            "!c1(V1), !ge(V2)\n" +
//            "!i(V1), !cd(V2)\n" +
//            "!cd(V1), !c2(V1)\n" +
//            "!cd(V1), !ge(V1)\n" +
//            "!ni(V1), !p3(V2)\n" +
//            "!n1(V1), !s2(V1)\n" +
//            "!car(V1), !so2(V1)\n" +
//            "!2_bond(V2, V1), !1_bond(V1, V2)\n" +
//            "!2_bond(V1, V2), !1_bond(V1, V2)\n" +
//            "!ni(V1), !s2(V2)\n" +
//            "!pd(V1), !br(V1)\n" +
//            "!n3(V1), !fe(V1)\n" +
//            "!pd(V1), !hg(V1)\n" +
//            "!ru(V1), !eu(V1)\n" +
//            "!zn(V1), !c2(V2)\n" +
//            "!n1(V1), !p3(V1)\n" +
//            "!2_bond(V1, V3), !ni(V1)\n" +
//            "!c1(V1), !pb(V2)\n" +
//            "!eu(V1), !br(V2)\n" +
//            "!2_bond(V1, V2), !ru(V2)\n" +
//            "!ru(V1), !ru(V2)\n" +
//            "!cl(V2), !am_bond(V1, V2)\n" +
//            "!fe(V1), !so2(V1)\n" +
//            "!ni(V1), !so2(V1)\n" +
//            "!ar_bond(V1, V3), !n2(V1)\n" +
//            "!cl(V1), !so2(V1)\n" +
//            "!f(V1), !cd(V2)\n" +
//            "!ni(V1), !c3(V1)\n" +
//            "!am_bond(V1, V2), !n4(V2)\n" +
//            "!ar_bond(V1, V2), !s3(V1)\n" +
//            "!2_bond(V1, V3), !eu(V1)\n" +
//            "!npl3(V1), !au(V2)\n" +
//            "!nar(V1), !ru(V1)\n" +
//            "!so(V1), !am_bond(V1, V2)\n" +
//            "!zn(V1), !nam(V2)\n" +
//            "!so(V1), !oco2(V1)\n" +
//            "!pb(V1), !nam(V1)\n" +
//            "!zn(V1), !s2(V1)\n" +
//            "!cu(V1), !as(V2)\n" +
//            "!oco2(V1), !nam(V1)\n" +
//            "!ar_bond(V1, V2), !cl(V1)\n" +
//            "!eu(V1), !p3(V2)\n" +
//            "!o2(V1), !se(V1)\n" +
//            "!i(V1), !br(V1)\n" +
//            "!oco2(V1), !i(V1)\n" +
//            "!o3(V1), !am_bond(V1, V2)\n" +
//            "!ru(V1), !sn(V1)\n" +
//            "!ni(V1), !hg(V2)\n" +
//            "!2_bond(V2, V1), !eu(V1)\n" +
//            "!car(V1), !n3(V1)\n" +
//            "!eu(V1), !c3(V1)\n" +
//            "!n3(V1), !ni(V1)\n" +
//            "!ar_bond(V2, V1), !ni(V1)\n" +
//            "!hg(V1), !f(V1)\n" +
//            "!cr(V1), !as(V2)\n" +
//            "!3_bond(V1, V2), !nam(V2)\n" +
//            "!cr(V1), !so2(V1)\n" +
//            "!pt(V1), !eu(V2)\n" +
//            "!cd(V1), !n2(V1)\n" +
//            "!pt(V1), !f(V1)\n" +
//            "!f(V1), !br(V1)\n" +
//            "!n2(V1), !pd(V2)\n" +
//            "!n4(V1), !pb(V2)\n" +
//            "!zn(V1), !p3(V1)\n" +
//            "!pd(V1), !cd(V2)\n" +
//            "!pt(V1), !s3(V2)\n" +
//            "!sn(V1), !br(V2)\n" +
//            "!sn(V1), !p3(V2)\n" +
//            "!zn(V1), !pb(V2)\n" +
//            "1_bond(V1, V3), !cd(V1)\n" +
//            "!nar(V1), !c3(V1)\n" +
//            "!cu(V1), !s2(V1)\n" +
//            "!hg(V2), !3_bond(V1, V2)\n" +
//            "!oco2(V1), !f(V1)\n" +
//            "!nar(V1), !so2(V1)\n" +
//            "!ar_bond(V1, V3), !ge(V1)\n" +
//            "!cr(V1), !c2(V2)\n" +
//            "!cr(V1), !s2(V1)\n" +
//            "!so(V1), !as(V1)\n" +
//            "!am_bond(V1, V2), !fe(V2)\n" +
//            "!cu(V1), !so2(V1)\n" +
//            "!eu(V1), !hg(V2)\n" +
//            "!ge(V1), !npl3(V2)\n" +
//            "!au(V1), !br(V1)\n" +
//            "!n1(V1), !pb(V2)\n" +
//            "!au(V1), !hg(V1)\n" +
//            "!3_bond(V2, V1), !sn(V1)\n" +
//            "!ge(V1), !cu(V2)\n" +
//            "!so2(V1), !ru(V2)\n" +
//            "!au(V1), !pt(V1)\n" +
//            "!o3(V1), !pb(V1)\n" +
//            "!se(V2), !ar_bond(V1, V2)\n" +
//            "!c3(V1), !ru(V2)\n" +
//            "!pt(V1), !i(V2)\n" +
//            "!nam(V1), !ge(V1)\n" +
//            "!am_bond(V1, V2), !cd(V1)\n" +
//            "!hg(V1), !cr(V2)\n" +
//            "!3_bond(V1, V2), !1_bond(V1, V2)\n" +
//            "!3_bond(V2, V1), !1_bond(V1, V2)\n" +
//            "!s3(V1), !br(V2)\n" +
//            "!cu(V1), !c3(V1)\n" +
//            "!ge(V1), !au(V2)\n" +
//            "!pd(V1), !pb(V2)\n" +
//            "!br(V1), !cr(V2)\n" +
//            "!pt(V1), !cr(V2)\n" +
//            "!s2(V1), !eu(V2)\n" +
//            "!pt(V1), !n1(V2)\n" +
//            "!as(V1), !nam(V1)\n" +
//            "!f(V2), !am_bond(V1, V2)\n" +
//            "!3_bond(V2, V1), !c3(V1)\n" +
//            "!ru(V1), !fe(V1)\n" +
//            "!eu(V1), !se(V1)\n" +
//            "!cr(V1), !nam(V2)\n" +
//            "!c2(V1), !nam(V1)\n" +
//            "!zn(V1), !n3(V1)\n" +
//            "!so(V1), !pt(V1)\n" +
//            "!pt(V1), !n4(V2)\n" +
//            "!3_bond(V2, V1), !so2(V1)\n" +
//            "!ru(V1), !cl(V1)\n" +
//            "!so(V1), !br(V1)\n" +
//            "!hg(V1), !so(V1)\n" +
//            "!ge(V1), !o3(V2)\n" +
//            "!cr(V1), !c3(V1)\n" +
//            "!s2(V1), !zn(V2)\n" +
//            "!n1(V1), !ar_bond(V1, V2)\n" +
//            "!br(V1), !n1(V2)\n" +
//            "1_bond(V1, V2), !ru(V1)\n" +
//            "!c1(V1), !as(V2)\n" +
//            "!se(V1), !ru(V2)\n" +
//            "!au(V1), !s2(V1)\n" +
//            "!cd(V1), !pb(V1)\n" +
//            "!eu(V1), !o2(V1)\n" +
//            "!eu(V1), !so2(V1)\n" +
//            "!ge(V1), !so(V2)\n" +
//            "!c1(V1), !pt(V2)\n" +
//            "!oco2(V1), !cd(V1)\n" +
//            "!as(V1), !i(V1)\n" +
//            "!nar(V1), !n3(V1)\n" +
//            "!cd(V1), !npl3(V1)\n" +
//            "!cu(V1), !hg(V2)\n" +
//            "!fe(V1), !br(V2)\n" +
//            "!am_bond(V1, V2), !pd(V2)\n" +
//            "!i(V1), !ge(V1)\n" +
//            "!zn(V1), !ar_bond(V1, V2)\n" +
//            "!s3(V1), !c3(V1)\n" +
//            "!s3(V1), !so2(V1)\n" +
//            "!3_bond(V2, V1), !se(V1)\n" +
//            "!3_bond(V1, V2), !o2(V1)\n" +
//            "!i(V1), !pb(V1)\n" +
//            "!am_bond(V1, V2), !f(V1)\n" +
//            "!n2(V1), !nam(V1)\n" +
//            "!ar_bond(V1, V2), !cu(V1)\n" +
//            "!c1(V1), !s2(V1)\n" +
//            "!n4(V1), !as(V2)\n" +
//            "!n2(V1), !au(V2)\n" +
//            "!se(V1), !sn(V1)\n" +
//            "!o3(V1), !pt(V1)\n" +
//            "!se(V1), !n3(V2)\n" +
//            "!au(V1), !p3(V1)\n" +
//            "!n3(V1), !cu(V1)\n" +
//            "!c3(V1), !cl(V1)\n" +
//            "!o3(V1), !br(V1)\n" +
//            "!n3(V1), !cr(V1)\n" +
//            "!hg(V1), !o3(V1)\n" +
//            "!am_bond(V1, V2), !i(V1)\n" +
//            "!fe(V1), !c3(V1)\n" +
//            "!cd(V1), !3_bond(V1, V3)\n" +
//            "!n4(V1), !s2(V1)\n" +
//            "!n3(V1), !s3(V1)\n" +
//            "!ar_bond(V1, V2), !c3(V2)\n" +
//            "!am_bond(V1, V2), !i(V2)\n" +
//            "!zn(V1), !as(V2)\n" +
//            "!pt(V1), !fe(V2)\n" +
//            "!ar_bond(V1, V2), !cr(V1)\n" +
//            "!cu(V2), !am_bond(V1, V2)\n" +
//            "!n1(V1), !as(V2)\n" +
//            "!n2(V1), !so(V2)\n" +
//            "!npl3(V1), !so(V2)\n" +
//            "!am_bond(V1, V2), !npl3(V2)\n" +
//            "!f(V1), !pb(V1)\n" +
//            "!ni(V1), !ru(V1)\n" +
//            "!o2(V1), !sn(V1)\n" +
//            "!au(V1), !pb(V2)\n" +
//            "!ni(V1), !br(V2)\n" +
//            "!p3(V1), !zn(V2)\n" +
//            "!c1(V1), !p3(V1)\n" +
//            "!cl(V1), !hg(V2)\n";
//    public static  String parentsCount = "2_bond(V2, V1), !2_bond(V1, V2)\n" +
//            "ar_bond(V2, V1), !ar_bond(V1, V2)\n" +
//            "1_bond(V2, V1), !1_bond(V1, V2)\n" +
//            "3_bond(V1, V2), !3_bond(V2, V1)\n" +
//            "am_bond(V2, V1), !am_bond(V1, V2)\n" +
//            "!eu(V1), !as(V2)\n" +
//            "!fe(V1), !pb(V2)\n" +
//            "!nar(V1), !ge(V1)\n" +
//            "!n3(V1), !se(V1)\n" +
//            "!am_bond(V1, V2), !au(V2)\n" +
//            "!car(V1), !p3(V1)\n" +
//            "!au(V1), !pb(V1)\n" +
//            "!pd(V1), !as(V1)\n" +
//            "!au(V1), !ar_bond(V1, V2)\n" +
//            "!nar(V1), !as(V1)\n" +
//            "!o3(V1), !n2(V1)\n" +
//            "!n3(V1), !o2(V1)\n" +
//            "!cl(V1), !pb(V2)\n" +
//            "!ge(V1), !f(V2)\n" +
//            "!1_bond(V1, V2), !n1(V2)\n" +
//            "!p3(V1), !se(V2)\n" +
//            "!am_bond(V1, V2), !o3(V2)\n" +
//            "!nar(V1), !c2(V1)\n" +
//            "!au(V1), !nam(V1)\n" +
//            "!pd(V1), !c2(V1)\n" +
//            "!se(V1), !hg(V2)\n" +
//            "!ge(V1), !so2(V2)\n" +
//            "!ar_bond(V1, V2), !zn(V2)\n" +
//            "!pd(V1), !ge(V1)\n" +
//            "!f(V1), !n2(V1)\n" +
//            "!eu(V1), !s2(V1)\n" +
//            "!c1(V1), !pb(V1)\n" +
//            "!car(V1), !s2(V1)\n" +
//            "!hg(V1), !cu(V1)\n" +
//            "!pt(V1), !cu(V1)\n" +
//            "!ni(V1), !p3(V1)\n" +
//            "!2_bond(V2, V1), !c3(V1)\n" +
//            "!cu(V1), !br(V1)\n" +
//            "!n2(V1), !i(V2)\n" +
//            "!pt(V1), !so2(V1)\n" +
//            "!so(V1), !au(V2)\n" +
//            "!ar_bond(V1, V2), !s3(V2)\n" +
//            "!i(V1), !c2(V1)\n" +
//            "!as(V1), !f(V1)\n" +
//            "!2_bond(V1, V2), !ru(V1)\n" +
//            "!pd(V1), !n2(V1)\n" +
//            "!n4(V1), !pb(V1)\n" +
//            "!sn(V1), !as(V2)\n" +
//            "!3_bond(V1, V2), !au(V2)\n" +
//            "!c1(V1), !nam(V1)\n" +
//            "!hg(V1), !cr(V1)\n" +
//            "!ni(V1), !pb(V2)\n" +
//            "!am_bond(V1, V2), !1_bond(V1, V2)\n" +
//            "!1_bond(V2, V1), !am_bond(V1, V2)\n" +
//            "!nar(V1), !n2(V1)\n" +
//            "!n4(V1), !ar_bond(V1, V2)\n" +
//            "!cr(V1), !br(V1)\n" +
//            "!hg(V1), !o2(V2)\n" +
//            "!sn(V1), !pt(V2)\n" +
//            "!ge(V1), !eu(V2)\n" +
//            "!f(V1), !ge(V1)\n" +
//            "!pt(V1), !ar_bond(V1, V3)\n" +
//            "!n3(V1), !npl3(V1)\n" +
//            "!f(V1), !c2(V1)\n" +
//            "!s2(V1), !ru(V2)\n" +
//            "!ar_bond(V2, V1), !c1(V1)\n" +
//            "!pt(V1), !cr(V1)\n" +
//            "!ru(V1), !c3(V1)\n" +
//            "!eu(V1), !p3(V1)\n" +
//            "!au(V1), !as(V1)\n" +
//            "!npl3(V1), !c3(V1)\n" +
//            "!am_bond(V1, V2), !so(V2)\n" +
//            "!p3(V1), !ru(V2)\n" +
//            "!n3(V1), !3_bond(V1, V3)\n" +
//            "!ru(V1), !hg(V2)\n" +
//            "!s3(V1), !br(V1)\n" +
//            "!hg(V1), !s3(V1)\n" +
//            "!pt(V1), !s3(V1)\n" +
//            "!pt(V1), !cl(V1)\n" +
//            "!ge(V1), !i(V2)\n" +
//            "!o3(V1), !au(V2)\n" +
//            "!au(V1), !ge(V1)\n" +
//            "!i(V1), !n2(V1)\n" +
//            "!au(V1), !c2(V1)\n" +
//            "!so(V1), !so(V2)\n" +
//            "!sn(V1), !s2(V1)\n" +
//            "!n4(V1), !nam(V1)\n" +
//            "!n1(V1), !pb(V1)\n" +
//            "!ru(V1), !oco2(V2)\n" +
//            "!ge(V1), !s3(V2)\n" +
//            "!hg(V1), !cl(V1)\n" +
//            "!ge(V1), !cr(V2)\n" +
//            "!n3(V1), !eu(V1)\n" +
//            "!cu(V1), !p3(V1)\n" +
//            "!zn(V1), !nam(V1)\n" +
//            "!ge(V1), !n4(V2)\n" +
//            "!eu(V1), !npl3(V2)\n" +
//            "!s3(V1), !s2(V1)\n" +
//            "!cl(V1), !as(V2)\n" +
//            "!fe(V1), !as(V2)\n" +
//            "!ru(V1), !se(V1)\n" +
//            "!oco2(V1), !c2(V1)\n" +
//            "!pd(V1), !ar_bond(V1, V2)\n" +
//            "!n1(V1), !nam(V1)\n" +
//            "!so2(V1), !p3(V1)\n" +
//            "!am_bond(V2, V1), !n2(V1)\n" +
//            "!ge(V1), !n1(V2)\n" +
//            "!pt(V1), !zn(V2)\n" +
//            "!so(V1), !c2(V1)\n" +
//            "!c3(V1), !sn(V1)\n" +
//            "!ni(V1), !nar(V2)\n" +
//            "!ar_bond(V1, V2), !so2(V2)\n" +
//            "!hg(V1), !c1(V1)\n" +
//            "!br(V1), !zn(V2)\n" +
//            "!so(V1), !ge(V1)\n" +
//            "!i(V1), !so(V2)\n" +
//            "!hg(V1), !zn(V2)\n" +
//            "!cd(V1), cl(V2)\n" +
//            "!3_bond(V1, V3), !p3(V1)\n" +
//            "!c1(V1), !br(V1)\n" +
//            "!f(V1), !so(V2)\n" +
//            "!cl(V1), !s2(V1)\n" +
//            "!c1(V1), !pt(V1)\n" +
//            "!cr(V1), !p3(V1)\n" +
//            "!ar_bond(V1, V2), !fe(V2)\n" +
//            "!s3(V1), !p3(V1)\n" +
//            "!oco2(V1), !n2(V1)\n" +
//            "!sn(V1), !hg(V2)\n" +
//            "!3_bond(V1, V2), !oco2(V1)\n" +
//            "!3_bond(V2, V1), !am_bond(V1, V2)\n" +
//            "!ru(V1), !o2(V1)\n" +
//            "!3_bond(V1, V2), !am_bond(V1, V2)\n" +
//            "!pd(V1), !pb(V1)\n" +
//            "!ge(V1), !cl(V2)\n" +
//            "!nar(V1), !au(V2)\n" +
//            "!cu(V1), !pb(V2)\n" +
//            "!npl3(V1), !s2(V1)\n" +
//            "!cd(V1), !au(V2)\n" +
//            "!n2(V1), !cu(V2)\n" +
//            "!br(V1), !so2(V1)\n" +
//            "!fe(V1), !s2(V1)\n" +
//            "!3_bond(V2, V1), !so(V1)\n" +
//            "!se(V1), !br(V2)\n" +
//            "!n4(V1), !br(V1)\n" +
//            "!o3(V1), !as(V1)\n" +
//            "!hg(V1), !so2(V1)\n" +
//            "!2_bond(V1, V2), !n3(V1)\n" +
//            "!ni(V1), !pt(V2)\n" +
//            "!so2(V1), !pb(V2)\n" +
//            "!s2(V1), !se(V2)\n" +
//            "!n2(V1), !cr(V2)\n" +
//            "!3_bond(V1, V3), !s2(V1)\n" +
//            "!n4(V1), !hg(V1)\n" +
//            "!n1(V1), !pt(V1)\n" +
//            "!car(V1), !br(V1)\n" +
//            "!ni(V1), !as(V2)\n" +
//            "!n4(V1), !pt(V1)\n" +
//            "!se(V1), !c3(V1)\n" +
//            "!o3(V1), !ge(V1)\n" +
//            "!fe(V1), !p3(V1)\n" +
//            "!n1(V1), !hg(V1)\n" +
//            "!hg(V1), !car(V1)\n" +
//            "!3_bond(V1, V2), !c2(V1)\n" +
//            "!cl(V1), !p3(V1)\n" +
//            "!n3(V1), !sn(V1)\n" +
//            "!so(V1), !n2(V1)\n" +
//            "!cr(V1), !pb(V2)\n" +
//            "!nar(V1), !nam(V1)\n" +
//            "!car(V1), !pt(V1)\n" +
//            "!cd(V1), !o3(V2)\n" +
//            "!nam(V1), !so2(V1)\n" +
//            "!ge(V1), !fe(V2)\n" +
//            "!i(V1), !au(V2)\n" +
//            "!zn(V1), !br(V1)\n" +
//            "!sn(V1), !nar(V2)\n" +
//            "!oco2(V1), !as(V1)\n" +
//            "!zn(V1), !hg(V1)\n" +
//            "!se(V1), !oco2(V2)\n" +
//            "!ar_bond(V1, V2), !eu(V2)\n" +
//            "!3_bond(V2, V1), !ge(V1)\n" +
//            "!o2(V1), !c3(V1)\n" +
//            "!cd(V1), !so(V2)\n" +
//            "!n1(V1), !br(V1)\n" +
//            "!o3(V1), !c2(V1)\n" +
//            "!cr(V1), !nam(V1)\n" +
//            "!zn(V1), !pt(V1)\n" +
//            "!ni(V1), !s2(V1)\n" +
//            "!f(V1), !nam(V1)\n" +
//            "!3_bond(V1, V2), !as(V1)\n" +
//            "!cu(V1), !nam(V1)\n" +
//            "!2_bond(V1, V3), !c3(V1)\n" +
//            "!hg(V2), !2_bond(V1, V2)\n" +
//            "!oco2(V1), !ge(V1)\n" +
//            "!i(V1), !nam(V1)\n" +
//            "!c1(V1), !cd(V2)\n" +
//            "!f(V1), !au(V2)\n" +
//            "!s3(V1), !pb(V2)\n" +
//            "!nar(V1), !pt(V1)\n" +
//            "!pb(V1), !cl(V1)\n" +
//            "!hg(V1), !nar(V1)\n" +
//            "!ru(V1), !as(V2)\n" +
//            "!ar_bond(V1, V2), !n4(V2)\n" +
//            "!n4(V1), !n2(V1)\n" +
//            "!pt(V1), !ru(V2)\n" +
//            "!cd(V1), !fe(V2)\n" +
//            "!o3(V1), !cd(V1)\n" +
//            "!ar_bond(V1, V2), !cu(V2)\n" +
//            "!car(V1), !cd(V2)\n" +
//            "!n1(V1), !n2(V1)\n" +
//            "!au(V1), !au(V2)\n" +
//            "!nar(V1), !br(V1)\n" +
//            "!ru(V1), !c2(V2)\n" +
//            "!pb(V1), !fe(V1)\n" +
//            "!ar_bond(V1, V2), !so(V1)\n" +
//            "!se(V1), !pb(V2)\n" +
//            "!oco2(V1), !pb(V1)\n" +
//            "!ni(V1), !cd(V2)\n" +
//            "!pd(V1), !pd(V2)\n" +
//            "!so(V1), !nam(V1)\n" +
//            "!so(V1), !cr(V2)\n" +
//            "!as(V1), !cu(V1)\n" +
//            "!am_bond(V1, V2), !oco2(V1)\n" +
//            "!zn(V1), !n2(V1)\n" +
//            "!br(V1), !ru(V2)\n" +
//            "!ru(V1), !nam(V2)\n" +
//            "!hg(V1), !sn(V1)\n" +
//            "!pd(V1), !cd(V1)\n" +
//            "!cu(V1), !ge(V1)\n" +
//            "!ar_bond(V1, V2), !cr(V2)\n" +
//            "!ru(V1), !s2(V1)\n" +
//            "!cu(V1), !c2(V1)\n" +
//            "!as(V1), !cr(V1)\n" +
//            "!sn(V1), !oco2(V2)\n" +
//            "!pd(V1), !3_bond(V2, V1)\n" +
//            "!car(V1), !am_bond(V1, V3)\n" +
//            "!ru(V1), !p3(V1)\n" +
//            "!so2(V1), !hg(V2)\n" +
//            "!am_bond(V1, V2), !as(V1)\n" +
//            "!c1(V1), !au(V2)\n" +
//            "!cr(V1), !ge(V1)\n" +
//            "!cl(V2), !ar_bond(V1, V2)\n" +
//            "!br(V1), !sn(V1)\n" +
//            "!car(V1), !pb(V1)\n" +
//            "!ni(V1), !pb(V1)\n" +
//            "!s2(V1), !br(V2)\n" +
//            "!3_bond(V2, V1), !nar(V1)\n" +
//            "!so(V1), !n1(V2)\n" +
//            "!eu(V1), !cd(V2)\n" +
//            "!ge(V1), !o2(V2)\n" +
//            "!pt(V1), !sn(V1)\n" +
//            "!cr(V1), !c2(V1)\n" +
//            "!sn(V1), !n2(V2)\n" +
//            "!npl3(V1), !pb(V2)\n" +
//            "!as(V1), !s3(V1)\n" +
//            "!ar_bond(V1, V2), !o3(V1)\n" +
//            "!n4(V1), !au(V2)\n" +
//            "!eu(V1), !oco2(V2)\n" +
//            "!s3(V1), !c2(V1)\n" +
//            "!c3(V1), !s2(V1)\n" +
//            "!p3(V1), !br(V2)\n" +
//            "!cd(V1), !i(V1)\n" +
//            "!ar_bond(V1, V2), !n1(V2)\n" +
//            "!c3(V1), !so2(V1)\n" +
//            "!so(V1), !o3(V1)\n" +
//            "!s3(V1), !ge(V1)\n" +
//            "!i(V1), !pd(V2)\n" +
//            "!npl3(V1), !p3(V1)\n" +
//            "!am_bond(V2, V1), !ge(V1)\n" +
//            "!as(V1), !cl(V1)\n" +
//            "!so2(V1), !s2(V1)\n" +
//            "!as(V1), !fe(V1)\n" +
//            "!cu(V1), !n2(V1)\n" +
//            "!n3(V1), !as(V2)\n" +
//            "!c1(V1), !so(V2)\n" +
//            "!ge(V1), !cl(V1)\n" +
//            "!so2(V2), !am_bond(V1, V2)\n" +
//            "!o3(V1), !cr(V2)\n" +
//            "!cd(V1), !f(V1)\n" +
//            "!ge(V1), !sn(V2)\n" +
//            "!nar(V1), !cd(V2)\n" +
//            "!sn(V1), !p3(V1)\n" +
//            "!se(V1), !as(V2)\n" +
//            "!br(V1), !cl(V1)\n" +
//            "!f(V1), !n1(V2)\n" +
//            "!i(V1), !cl(V2)\n" +
//            "!pt(V1), !fe(V1)\n" +
//            "!ar_bond(V1, V2), !i(V2)\n" +
//            "!hg(V1), !fe(V1)\n" +
//            "!br(V1), !fe(V1)\n" +
//            "!eu(V1), !pb(V2)\n" +
//            "!c1(V1), !as(V1)\n" +
//            "!o3(V1), !nam(V1)\n" +
//            "!cu(V1), !cd(V2)\n" +
//            "!ar_bond(V1, V2), !pd(V2)\n" +
//            "!ge(V1), !zn(V2)\n" +
//            "!n3(V1), !ru(V1)\n" +
//            "!zn(V1), !pb(V1)\n" +
//            "!n2(V1), !eu(V2)\n" +
//            "!se(V1), !s2(V1)\n" +
//            "!ge(V1), !ni(V2)\n" +
//            "!pd(V1), !nam(V1)\n" +
//            "!c1(V1), !ge(V1)\n" +
//            "!o3(V1), !i(V1)\n" +
//            "!c1(V1), !c2(V1)\n" +
//            "!pt(V1), !se(V2)\n" +
//            "!ar_bond(V1, V2), !cd(V1)\n" +
//            "!o2(V1), !s2(V1)\n" +
//            "!cd(V1), !cr(V2)\n" +
//            "!i(V1), !f(V1)\n" +
//            "!ni(V1), !oco2(V2)\n" +
//            "!s3(V1), !cd(V2)\n" +
//            "!oco2(V1), !nar(V1)\n" +
//            "!ar_bond(V1, V2), !f(V2)\n" +
//            "!3_bond(V1, V2), !zn(V2)\n" +
//            "!am_bond(V1, V2), !nar(V1)\n" +
//            "!cd(V1), !n4(V2)\n" +
//            "!so(V1), !cd(V1)\n" +
//            "!au(V1), !n2(V1)\n" +
//            "!n4(V1), !as(V1)\n" +
//            "!nar(V1), !pb(V1)\n" +
//            "!so2(V1), !as(V2)\n" +
//            "!i(V1), !fe(V2)\n" +
//            "!pd(V1), !au(V2)\n" +
//            "!pt(V1), !ni(V1)\n" +
//            "!pt(V1), !npl3(V1)\n" +
//            "!n3(V1), !c3(V1)\n" +
//            "!n1(V1), !as(V1)\n" +
//            "!n4(V1), !c2(V1)\n" +
//            "!hg(V1), !ni(V1)\n" +
//            "!hg(V1), !npl3(V1)\n" +
//            "!pd(V1), !o3(V2)\n" +
//            "!n3(V1), !so2(V1)\n" +
//            "!pb(V1), !cu(V1)\n" +
//            "!n4(V1), !ge(V1)\n" +
//            "!cd(V1), !n1(V2)\n" +
//            "!c1(V1), !am_bond(V1, V3)\n" +
//            "!se(V1), !p3(V1)\n" +
//            "!i(V1), !n4(V2)\n" +
//            "!car(V1), !ge(V1)\n" +
//            "!cd(V2), !am_bond(V1, V2)\n" +
//            "!n2(V1), !zn(V2)\n" +
//            "!npl3(V1), !br(V1)\n" +
//            "!cr(V1), !pb(V1)\n" +
//            "!n1(V1), !ge(V1)\n" +
//            "!i(V1), !f(V2)\n" +
//            "!am_bond(V1, V2), !pb(V1)\n" +
//            "!car(V1), !c2(V1)\n" +
//            "!sn(V1), !pb(V2)\n" +
//            "!ni(V1), !br(V1)\n" +
//            "!i(V1), !cr(V2)\n" +
//            "!ar_bond(V1, V2), !f(V1)\n" +
//            "!3_bond(V2, V1), !br(V1)\n" +
//            "!car(V1), !as(V1)\n" +
//            "!3_bond(V1, V2), !hg(V1)\n" +
//            "!cu(V1), !nar(V2)\n" +
//            "!pb(V1), !s3(V1)\n" +
//            "!zn(V1), !ge(V1)\n" +
//            "!hg(V1), !oco2(V1)\n" +
//            "!zn(V1), !c2(V1)\n" +
//            "!oco2(V1), !pt(V1)\n" +
//            "!n1(V1), !c2(V1)\n" +
//            "!c1(V1), !n2(V1)\n" +
//            "!so(V1), !i(V1)\n" +
//            "!ar_bond(V1, V2), !nam(V2)\n" +
//            "!pt(V1), !eu(V1)\n" +
//            "!o2(V1), !p3(V1)\n" +
//            "!zn(V1), !as(V1)\n" +
//            "!hg(V1), !eu(V1)\n" +
//            "!oco2(V1), !br(V1)\n" +
//            "!2_bond(V1, V2), !pb(V2)\n" +
//            "!pd(V1), !so(V2)\n" +
//            "!ar_bond(V1, V2), !so2(V1)\n" +
//            "!ge(V1), !car(V2)\n" +
//            "!so(V1), !f(V1)\n" +
//            "!3_bond(V2, V1), !n4(V1)\n" +
//            "!f(V1), !cr(V2)\n" +
//            "!n3(V1), !hg(V2)\n" +
//            "!i(V1), !n1(V2)\n" +
//            "!3_bond(V1, V3), !br(V1)\n" +
//            "!eu(V1), !br(V1)\n" +
//            "!f(V1), !n4(V2)\n" +
//            "!fe(V1), !oco2(V2)\n" +
//            "!ar_bond(V1, V2), !i(V1)\n" +
//            "!pt(V1), !3_bond(V1, V3)\n" +
//            "!n2(V1), !ni(V2)\n" +
//            "!pb(V1), !se(V1)\n" +
//            "!as(V1), !nam(V2)\n" +
//            "!eu(V1), !c2(V1)\n" +
//            "!ni(V1), !n2(V1)\n" +
//            "!pd(V1), !fe(V2)\n" +
//            "!2_bond(V1, V3), !br(V1)\n" +
//            "!cu(V1), !so(V2)\n" +
//            "!zn(V1), !cd(V1)\n" +
//            "!pb(V1), !o2(V1)\n" +
//            "!s3(V1), !au(V2)\n" +
//            "!n1(V1), !cd(V1)\n" +
//            "!ru(V1), !br(V1)\n" +
//            "!pt(V1), !p3(V2)\n" +
//            "!au(V1), !cr(V2)\n" +
//            "!ge(V1), !ru(V2)\n" +
//            "!pd(V1), !o3(V1)\n" +
//            "!nar(V1), !f(V1)\n" +
//            "!so2(V1), !so(V2)\n" +
//            "!s2(V1), !as(V2)\n" +
//            "!ni(V1), !3_bond(V1, V3)\n" +
//            "!oco2(V1), !pb(V2)\n" +
//            "!car(V1), !n2(V1)\n" +
//            "!3_bond(V2, V1), !pt(V1)\n" +
//            "!so2(V1), !au(V2)\n" +
//            "!au(V1), !so(V1)\n" +
//            "!o3(V1), !nar(V1)\n" +
//            "!nam(V1), !o2(V1)\n" +
//            "!cu(V1), !oco2(V2)\n" +
//            "!as(V1), !sn(V1)\n" +
//            "!npl3(V1), !cl(V1)\n" +
//            "!c1(V1), !cu(V2)\n" +
//            "!nam(V1), !se(V1)\n" +
//            "!ar_bond(V1, V2), !npl3(V1)\n" +
//            "!ar_bond(V1, V2), !au(V2)\n" +
//            "!so(V1), !zn(V2)\n" +
//            "!npl3(V1), !fe(V1)\n" +
//            "!fe(V1), !au(V2)\n" +
//            "!car(V1), !npl3(V1)\n" +
//            "!pt(V1), !s2(V2)\n" +
//            "!ge(V1), !nam(V2)\n" +
//            "!pt(V1), !c3(V1)\n" +
//            "!eu(V1), !n2(V1)\n" +
//            "!2_bond(V1, V3), !pt(V1)\n" +
//            "!am_bond(V1, V3), !sn(V1)\n" +
//            "!cr(V1), !oco2(V2)\n" +
//            "!c1(V1), !cr(V2)\n" +
//            "!pd(V1), !f(V2)\n" +
//            "!br(V1), !c3(V1)\n" +
//            "!hg(V1), !c3(V1)\n" +
//            "!s3(V1), !npl3(V1)\n" +
//            "!2_bond(V2, V1), !br(V1)\n" +
//            "!c1(V1), !n4(V2)\n" +
//            "!ar_bond(V1, V2), !so(V2)\n" +
//            "!so(V1), !c1(V1)\n" +
//            "!c2(V1), !sn(V1)\n" +
//            "!o3(V1), !f(V1)\n" +
//            "!n2(V1), !ru(V2)\n" +
//            "!p3(V1), !as(V2)\n" +
//            "!ge(V1), !sn(V1)\n" +
//            "!am_bond(V1, V2), !pt(V1)\n" +
//            "!fe(V1), !so(V2)\n" +
//            "!3_bond(V1, V3), !fe(V1)\n" +
//            "!cu(V1), !pd(V2)\n" +
//            "!f(V1), !fe(V2)\n" +
//            "!pd(V1), !nar(V1)\n" +
//            "!oco2(V1), !p3(V1)\n" +
//            "!au(V1), !3_bond(V1, V2)\n" +
//            "!npl3(V1), !so2(V1)\n" +
//            "!so2(V1), !pd(V2)\n" +
//            "!cd(V1), !cu(V1)\n" +
//            "!am_bond(V1, V2), !br(V1)\n" +
//            "!pt(V1), !hg(V2)\n" +
//            "!au(V1), !o3(V1)\n" +
//            "!3_bond(V1, V3), !cl(V1)\n" +
//            "!n4(V1), !cr(V2)\n" +
//            "!pd(V1), !i(V1)\n" +
//            "!n3(V1), !pt(V1)\n" +
//            "!hg(V1), !hg(V2)\n" +
//            "!br(V1), !hg(V2)\n" +
//            "!hg(V1), !2_bond(V1, V2)\n" +
//            "!hg(V1), !am_bond(V1, V2)\n" +
//            "!pd(V1), !f(V1)\n" +
//            "!n3(V1), !br(V1)\n" +
//            "!as(V1), !o2(V1)\n" +
//            "!n1(V1), !cr(V2)\n" +
//            "!cd(V1), !cr(V1)\n" +
//            "!ar_bond(V1, V2), !o3(V2)\n" +
//            "!n4(V1), !n1(V2)\n" +
//            "!cr(V1), !npl3(V1)\n" +
//            "!n4(V1), !so(V1)\n" +
//            "!as(V1), !se(V1)\n" +
//            "!p3(V1), !s2(V1)\n" +
//            "!hg(V1), !n3(V1)\n" +
//            "!nar(V1), !i(V1)\n" +
//            "!s3(V1), !3_bond(V1, V3)\n" +
//            "!2_bond(V2, V1), !pt(V1)\n" +
//            "!ni(V1), !au(V2)\n" +
//            "!fe(V1), !ge(V1)\n" +
//            "!c2(V1), !cl(V1)\n" +
//            "!zn(V1), !au(V2)\n" +
//            "!br(V1), !se(V1)\n" +
//            "!eu(V1), !pb(V1)\n" +
//            "!hg(V1), !o2(V1)\n" +
//            "!n1(V1), !au(V2)\n" +
//            "!c2(V1), !fe(V1)\n" +
//            "!ar_bond(V1, V2), !c2(V1)\n" +
//            "!zn(V1), !oco2(V2)\n" +
//            "!n4(V1), !so(V2)\n" +
//            "!npl3(V1), !ru(V2)\n" +
//            "!c1(V1), !f(V1)\n" +
//            "!ru(V1), !pb(V2)\n" +
//            "!n4(V1), !i(V1)\n" +
//            "!hg(V1), !se(V1)\n" +
//            "!sn(V1), !cd(V2)\n" +
//            "!au(V1), !cd(V1)\n" +
//            "!cr(V1), !n2(V1)\n" +
//            "!br(V1), !o2(V1)\n" +
//            "!c3(V1), !p3(V1)\n" +
//            "!s2(V1), !hg(V2)\n" +
//            "!pt(V1), !o2(V1)\n" +
//            "!cd(V1), !so2(V1)\n" +
//            "!pt(V1), !se(V1)\n" +
//            "!pd(V1), !n4(V2)\n" +
//            "!ge(V1), !se(V2)\n" +
//            "!3_bond(V1, V3), !sn(V1)\n" +
//            "!pd(V1), !so(V1)\n" +
//            "!pd(V1), !cr(V2)\n" +
//            "!nar(V1), !zn(V2)\n" +
//            "!cd(V1), !zn(V2)\n" +
//            "!eu(V1), !npl3(V1)\n" +
//            "!c1(V1), !i(V2)\n" +
//            "!3_bond(V1, V2), !pb(V1)\n" +
//            "!ar_bond(V2, V1), !ge(V1)\n" +
//            "!am_bond(V2, V2)\n" +
//            "!au(V1), !nar(V1)\n" +
//            "!ni(V1), !as(V1)\n" +
//            "!n2(V1), !cl(V1)\n" +
//            "!s3(V1), !n2(V1)\n" +
//            "!n3(V1), !s2(V1)\n" +
//            "!p3(V1), !hg(V2)\n" +
//            "!n1(V1), !i(V1)\n" +
//            "!n4(V1), !f(V1)\n" +
//            "!am_bond(V1, V2), !s2(V1)\n" +
//            "!oco2(V1), !s2(V1)\n" +
//            "!ru(V1), !nam(V1)\n" +
//            "!c1(V1), !nar(V1)\n" +
//            "!n2(V1), !fe(V1)\n" +
//            "!au(V1), !i(V1)\n" +
//            "!c1(V1), !cd(V1)\n" +
//            "!2_bond(V1, V2), !pb(V1)\n" +
//            "!car(V1), !nam(V1)\n" +
//            "!c1(V1), !pd(V2)\n" +
//            "!pd(V1), !cl(V2)\n" +
//            "!am_bond(V1, V2), !pb(V2)\n" +
//            "!pd(V1), !n1(V2)\n" +
//            "!cu(V1), !au(V2)\n" +
//            "!pb(V1), !sn(V1)\n" +
//            "!n3(V1), !p3(V1)\n" +
//            "!au(V1), !f(V1)\n" +
//            "!ni(V1), !ge(V1)\n" +
//            "!ni(V1), !c2(V1)\n" +
//            "!n4(V1), !nar(V1)\n" +
//            "!am_bond(V1, V2), !p3(V1)\n" +
//            "!n4(V1), !cd(V1)\n" +
//            "!nam(V1), !c3(V1)\n" +
//            "!as(V1), !eu(V1)\n" +
//            "!hg(V1), !ru(V1)\n" +
//            "!f(V1), !zn(V2)\n" +
//            "!ar_bond(V2, V1), !n2(V1)\n" +
//            "!pt(V1), !ru(V1)\n" +
//            "!eu(V1), !3_bond(V1, V3)\n" +
//            "!n3(V1), !pb(V2)\n" +
//            "!i(V1), !zn(V2)\n" +
//            "!c1(V1), !i(V1)\n" +
//            "!ni(V1), !npl3(V1)\n" +
//            "!eu(V1), !ge(V1)\n" +
//            "!pd(V1), !zn(V1)\n" +
//            "!cu(V1), !cl(V2)\n" +
//            "!i(V1), !cu(V1)\n" +
//            "!ar_bond(V1, V2), !oco2(V1)\n" +
//            "!sn(V1), !so(V2)\n" +
//            "!pt(V1), !s2(V1)\n" +
//            "!s3(V1), !cr(V2)\n" +
//            "!ru(V1), !c2(V1)\n" +
//            "!c1(V1), !eu(V2)\n" +
//            "!ge(V1), !br(V2)\n" +
//            "!eu(V1), !pd(V2)\n" +
//            "!ge(V1), !p3(V2)\n" +
//            "!3_bond(V2, V1), !i(V1)\n" +
//            "!br(V1), !s2(V1)\n" +
//            "!pd(V1), !n1(V1)\n" +
//            "!n3(V1), !pb(V1)\n" +
//            "!s3(V1), !nam(V1)\n" +
//            "!au(V1), !oco2(V2)\n" +
//            "!oco2(V1), !n3(V1)\n" +
//            "!hg(V1), !s2(V1)\n" +
//            "!so(V1), !s3(V1)\n" +
//            "!nam(V1), !fe(V1)\n" +
//            "!cd(V1), !eu(V1)\n" +
//            "!fe(V1), !cr(V2)\n" +
//            "!ar_bond(V1, V2), !am_bond(V1, V2)\n" +
//            "!o3(V1), !cu(V1)\n" +
//            "!ar_bond(V2, V1), !am_bond(V1, V2)\n" +
//            "!npl3(V1), !se(V1)\n" +
//            "!so(V1), !fe(V1)\n" +
//            "!se(V1), !au(V2)\n" +
//            "!f(V1), !cu(V1)\n" +
//            "!i(V1), !cr(V1)\n" +
//            "!ge(V1), !s2(V2)\n" +
//            "!au(V1), !c1(V1)\n" +
//            "!as(V1), !c3(V1)\n" +
//            "!pt(V1), !p3(V1)\n" +
//            "!fe(V1), !n4(V2)\n" +
//            "!ru(V1), !n2(V1)\n" +
//            "!so(V1), !cl(V1)\n" +
//            "!nar(V1), !cd(V1)\n" +
//            "!cu(V1), !fe(V2)\n" +
//            "!i(V1), !s3(V1)\n" +
//            "!fe(V1), !n1(V2)\n" +
//            "!hg(V1), !p3(V1)\n" +
//            "!ge(V1), !c3(V1)\n" +
//            "!nam(V1), !cl(V1)\n" +
//            "!pt(V1), !nam(V2)\n" +
//            "!npl3(V1), !o2(V1)\n" +
//            "!ar_bond(V1, V2), !pb(V1)\n" +
//            "!2_bond(V1, V3), !ge(V1)\n" +
//            "1_bond(V2, V1), !cd(V1)\n" +
//            "!cr(V1), !f(V1)\n" +
//            "!car(V1), !cd(V1)\n" +
//            "!cu(V1), !npl3(V2)\n" +
//            "!n1(V1), !f(V1)\n" +
//            "!c1(V1), !f(V2)\n" +
//            "!oco2(V1), !c3(V1)\n" +
//            "!se(V1), !so(V2)\n" +
//            "!c2(V1), !c3(V1)\n" +
//            "!ni(V1), !ar_bond(V1, V3)\n" +
//            "!au(V1), !n4(V1)\n" +
//            "!o3(V1), !s3(V1)\n" +
//            "1_bond(V1, V2), !cr(V2)\n" +
//            "!cd(V1), !ru(V2)\n" +
//            "!br(V1), !p3(V1)\n" +
//            "!o3(V1), !cr(V1)\n" +
//            "!car(V1), !cr(V2)\n" +
//            "!oco2(V1), !so2(V1)\n" +
//            "!zn(V1), !f(V1)\n" +
//            "!3_bond(V1, V2), !cr(V2)\n" +
//            "!ni(V1), !cu(V2)\n" +
//            "!c1(V1), !zn(V2)\n" +
//            "!zn(V1), !i(V1)\n" +
//            "!am_bond(V1, V2), !pt(V2)\n" +
//            "!3_bond(V2, V1), !n2(V1)\n" +
//            "!car(V1), !cu(V2)\n" +
//            "!3_bond(V1, V3), !se(V1)\n" +
//            "!ni(V1), !npl3(V2)\n" +
//            "!ni(V1), !n4(V2)\n" +
//            "!hg(V1), !pb(V2)\n" +
//            "!ar_bond(V1, V2), !as(V1)\n" +
//            "!so(V1), !ni(V1)\n" +
//            "!cd(V1), !sn(V1)\n" +
//            "!ni(V1), !cr(V2)\n" +
//            "!o3(V1), !cl(V1)\n" +
//            "!pt(V1), !pb(V2)\n" +
//            "!cd(V1), !n3(V2)\n" +
//            "!au(V1), !zn(V1)\n" +
//            "!npl3(V1), !sn(V1)\n" +
//            "!pd(V1), !cu(V1)\n" +
//            "!ni(V1), !nam(V1)\n" +
//            "!am_bond(V1, V2), !as(V2)\n" +
//            "!so2(V1), !cd(V2)\n" +
//            "!n1(V1), !au(V1)\n" +
//            "!n3(V1), !as(V1)\n" +
//            "!ge(V1), !hg(V2)\n" +
//            "!c1(V1), !ni(V2)\n" +
//            "!pd(V1), !oco2(V2)\n" +
//            "!n4(V1), !zn(V2)\n" +
//            "!sn(V1), !pd(V2)\n" +
//            "!n3(V1), !ge(V1)\n" +
//            "!o3(V1), !fe(V1)\n" +
//            "!pd(V1), !cr(V1)\n" +
//            "!n2(V1), !c3(V1)\n" +
//            "!n3(V1), !c2(V1)\n" +
//            "!n4(V1), !c1(V1)\n" +
//            "!2_bond(V2, V1), !ge(V1)\n" +
//            "!br(V1), !pb(V2)\n" +
//            "!3_bond(V2, V1), !f(V1)\n" +
//            "!s2(V1), !cd(V2)\n" +
//            "!n2(V1), !so2(V1)\n" +
//            "!am_bond(V1, V2), !br(V2)\n" +
//            "!ge(V1), !o2(V1)\n" +
//            "!c2(V1), !se(V1)\n" +
//            "!cd(V1), !s3(V1)\n" +
//            "!am_bond(V1, V2), !p3(V2)\n" +
//            "!am_bond(V1, V2), !s2(V2)\n" +
//            "!s2(V1), !pb(V2)\n" +
//            "!zn(V1), !so(V1)\n" +
//            "!i(V1), !se(V2)\n" +
//            "!ar_bond(V1, V2), !br(V1)\n" +
//            "!cd(V1), !o2(V2)\n" +
//            "!pd(V1), !au(V1)\n" +
//            "!n1(V1), !so(V1)\n" +
//            "!n2(V1), !sn(V1)\n" +
//            "!f(V1), !se(V2)\n" +
//            "!ge(V1), !se(V1)\n" +
//            "!c3(V1), !cd(V2)\n" +
//            "!oco2(V1), !ru(V1)\n" +
//            "!c2(V1), !o2(V1)\n" +
//            "!o3(V1), !c1(V1)\n" +
//            "!eu(V1), !au(V2)\n" +
//            "!zn(V1), !cr(V2)\n" +
//            "!ru(V1), !pb(V1)\n" +
//            "!am_bond(V1, V2), !ru(V1)\n" +
//            "!nar(V1), !cr(V2)\n" +
//            "!cd(V1), !cl(V1)\n" +
//            "!so(V1), !nar(V1)\n" +
//            "!n4(V1), !o3(V1)\n" +
//            "!ar_bond(V2, V1), !pt(V1)\n" +
//            "!3_bond(V1, V3), !c3(V1)\n" +
//            "!cd(V1), !fe(V1)\n" +
//            "!ar_bond(V1, V2), !hg(V1)\n" +
//            "!pd(V1), !zn(V2)\n" +
//            "!ru(V1), !npl3(V1)\n" +
//            "!ni(V1), !i(V1)\n" +
//            "!ni(V1), !so(V2)\n" +
//            "!3_bond(V1, V3), !so2(V1)\n" +
//            "!eu(V1), !o3(V2)\n" +
//            "!ni(V1), !f(V1)\n" +
//            "!eu(V1), !nam(V1)\n" +
//            "!so(V1), !car(V1)\n" +
//            "!c2(V1), !so2(V1)\n" +
//            "!3_bond(V2, V1), !cd(V1)\n" +
//            "!cu(V1), !cu(V2)\n" +
//            "!pb(V1), !c3(V1)\n" +
//            "!pd(V1), !c1(V1)\n" +
//            "!ge(V1), !so2(V1)\n" +
//            "!eu(V1), !so(V2)\n" +
//            "!n2(V1), !o2(V1)\n" +
//            "!i(V1), !so2(V2)\n" +
//            "!n1(V1), !o3(V1)\n" +
//            "!3_bond(V1, V2), !nam(V1)\n" +
//            "!cu(V1), !i(V2)\n" +
//            "!pb(V1), !so2(V1)\n" +
//            "!i(V1), !cl(V1)\n" +
//            "!as(V1), !so2(V1)\n" +
//            "!so(V1), !cu(V1)\n" +
//            "!cu(V1), !f(V2)\n" +
//            "!cd(V1), !se(V2)\n" +
//            "!p3(V1), !pb(V2)\n" +
//            "!cu(V1), !cr(V2)\n" +
//            "!am_bond(V1, V2), !so2(V1)\n" +
//            "!am_bond(V1, V2), !c3(V1)\n" +
//            "!n2(V1), !se(V1)\n" +
//            "!am_bond(V1, V2), !n3(V1)\n" +
//            "!f(V1), !s3(V1)\n" +
//            "!i(V1), !fe(V1)\n" +
//            "!cu(V1), !n4(V2)\n" +
//            "!s3(V1), !i(V2)\n" +
//            "!ru(V1), !3_bond(V1, V3)\n" +
//            "!cu(V1), !n1(V2)\n" +
//            "!ni(V1), !pd(V2)\n" +
//            "!as(V1), !ru(V1)\n" +
//            "!o3(V1), !car(V1)\n" +
//            "!2_bond(V1, V2), !au(V2)\n" +
//            "!sn(V1), !au(V2)\n" +
//            "!f(V1), !cl(V1)\n" +
//            "!so(V1), !oco2(V2)\n" +
//            "!ar_bond(V1, V3), !sn(V1)\n" +
//            "!hg(V1), !as(V2)\n" +
//            "!cr(V1), !cr(V2)\n" +
//            "!nam(V1), !sn(V1)\n" +
//            "!br(V1), !as(V2)\n" +
//            "!hg(V2), !am_bond(V1, V2)\n" +
//            "!ru(V1), !ge(V1)\n" +
//            "!cd(V1), !ni(V1)\n" +
//            "!f(V1), !fe(V1)\n" +
//            "!pt(V1), !pt(V2)\n" +
//            "!cd(V2), !ar_bond(V1, V2)\n" +
//            "!so(V1), !cr(V1)\n" +
//            "!pt(V1), !as(V2)\n" +
//            "!zn(V1), !o3(V1)\n" +
//            "!pd(V1), !n4(V1)\n" +
//            "!eu(V1), !i(V2)\n" +
//            "!ge(V1), !c2(V2)\n" +
//            "!f(V1), !3_bond(V1, V3)\n" +
//            "!oco2(V2), !am_bond(V1, V2)\n" +
//            "!nam(V1), !pb(V2)\n" +
//            "!c1(V1), !cr(V1)\n" +
//            "!2_bond(V2, V2)\n" +
//            "!au(V1), !cl(V1)\n" +
//            "!eu(V1), !so2(V2)\n" +
//            "!f(V1), !ru(V2)\n" +
//            "!oco2(V1), !o2(V1)\n" +
//            "!oco2(V1), !se(V1)\n" +
//            "!as(V1), !s2(V1)\n" +
//            "!c1(V1), !so2(V1)\n" +
//            "!3_bond(V2, V1), !2_bond(V1, V2)\n" +
//            "!3_bond(V1, V2), !2_bond(V1, V2)\n" +
//            "!n3(V1), !au(V2)\n" +
//            "!au(V1), !fe(V1)\n" +
//            "!c2(V1), !s2(V1)\n" +
//            "!c1(V1), !s3(V1)\n" +
//            "!ge(V1), !s2(V1)\n" +
//            "!n4(V1), !so2(V1)\n" +
//            "!n4(V1), !cu(V1)\n" +
//            "!i(V1), !sn(V1)\n" +
//            "!o3(V1), !ru(V2)\n" +
//            "!s3(V1), !zn(V2)\n" +
//            "!o2(V1), !cr(V2)\n" +
//            "!as(V1), !p3(V1)\n" +
//            "!n4(V1), !cr(V1)\n" +
//            "!n1(V1), !cu(V1)\n" +
//            "!f(V1), !sn(V1)\n" +
//            "!so(V1), !o2(V1)\n" +
//            "!o3(V1), !sn(V1)\n" +
//            "!cd(V1), !ru(V1)\n" +
//            "!pd(V1), !eu(V1)\n" +
//            "!am_bond(V1, V2), !se(V1)\n" +
//            "!se(V1), !cr(V2)\n" +
//            "!3_bond(V2, V1), !ru(V1)\n" +
//            "!i(V1), !3_bond(V1, V3)\n" +
//            "!npl3(V1), !cr(V2)\n" +
//            "!se(V1), !n1(V2)\n" +
//            "!ge(V1), !p3(V1)\n" +
//            "!n1(V1), !so2(V1)\n" +
//            "!ni(V1), !eu(V2)\n" +
//            "!sn(V1), !fe(V2)\n" +
//            "!ar_bond(V1, V2), !pb(V2)\n" +
//            "!c1(V1), !cl(V1)\n" +
//            "!so(V1), !se(V1)\n" +
//            "!se(V1), !n4(V2)\n" +
//            "!am_bond(V1, V2), !o2(V1)\n" +
//            "!pd(V1), !car(V1)\n" +
//            "!c1(V1), !fe(V1)\n" +
//            "!nar(V1), !eu(V1)\n" +
//            "!so(V1), !npl3(V1)\n" +
//            "!au(V1), !so2(V1)\n" +
//            "!fe(V1), !zn(V2)\n" +
//            "!n2(V1), !pt(V2)\n" +
//            "!cd(V1), !br(V2)\n" +
//            "!oco2(V1), !sn(V1)\n" +
//            "!sn(V1), !so2(V2)\n" +
//            "!pt(V1), !cd(V2)\n" +
//            "!am_bond(V1, V2), !npl3(V1)\n" +
//            "1_bond(V1, V2), !ru(V2)\n" +
//            "!n1(V1), !s3(V1)\n" +
//            "!zn(V1), !cr(V1)\n" +
//            "!n2(V1), !s2(V1)\n" +
//            "!c2(V1), !p3(V1)\n" +
//            "!n1(V1), !cr(V1)\n" +
//            "!zn(V1), !so2(V1)\n" +
//            "!ge(V1), !ge(V2)\n" +
//            "!ni(V1), !i(V2)\n" +
//            "!car(V1), !nar(V1)\n" +
//            "!n4(V1), !s3(V1)\n" +
//            "!zn(V1), !cu(V1)\n" +
//            "!pd(V1), !ru(V2)\n" +
//            "!npl3(V1), !i(V2)\n" +
//            "!au(V1), !ni(V1)\n" +
//            "!n4(V1), !cl(V1)\n" +
//            "!as(V1), !pb(V2)\n" +
//            "!i(V1), !eu(V1)\n" +
//            "!c3(V1), !pd(V2)\n" +
//            "!nar(V1), !ru(V2)\n" +
//            "!so(V1), !3_bond(V1, V3)\n" +
//            "!2_bond(V2, V1), !cd(V1)\n" +
//            "!au(V1), !car(V1)\n" +
//            "!eu(V1), !eu(V2)\n" +
//            "!c1(V1), !se(V2)\n" +
//            "!cd(V1), !c3(V1)\n" +
//            "!i(V1), !npl3(V1)\n" +
//            "!n4(V1), !fe(V1)\n" +
//            "!pt(V1), !pb(V1)\n" +
//            "!o3(V1), !se(V1)\n" +
//            "!ni(V1), !zn(V2)\n" +
//            "!f(V1), !eu(V1)\n" +
//            "!o3(V1), !o2(V1)\n" +
//            "!n2(V1), !p3(V1)\n" +
//            "!pb(V1), !br(V1)\n" +
//            "!n1(V1), !fe(V1)\n" +
//            "!pd(V1), !n3(V2)\n" +
//            "!nar(V1), !sn(V1)\n" +
//            "!zn(V1), !s3(V1)\n" +
//            "!cd(V1), !oco2(V2)\n" +
//            "!n1(V1), !cl(V1)\n" +
//            "!oco2(V2), !3_bond(V1, V2)\n" +
//            "!ni(V1), !f(V2)\n" +
//            "!car(V1), !f(V1)\n" +
//            "!hg(V1), !pb(V1)\n" +
//            "!pd(V1), !sn(V1)\n" +
//            "!i(V1), !ru(V2)\n" +
//            "!ge(V1), !pb(V2)\n" +
//            "!f(V1), !npl3(V1)\n" +
//            "!am_bond(V1, V2), !ru(V2)\n" +
//            "!oco2(V1), !eu(V1)\n" +
//            "!hg(V1), !nam(V1)\n" +
//            "!car(V1), !i(V1)\n" +
//            "!fe(V1), !fe(V2)\n" +
//            "!3_bond(V2, V1), !npl3(V1)\n" +
//            "!n4(V1), !car(V1)\n" +
//            "!ar_bond(V1, V2), !as(V2)\n" +
//            "!2_bond(V2, V1), !i(V1)\n" +
//            "!pt(V1), !nam(V1)\n" +
//            "!ni(V1), !cl(V2)\n" +
//            "!eu(V1), !n4(V2)\n" +
//            "!pd(V1), !o2(V2)\n" +
//            "!ni(V1), !n1(V2)\n" +
//            "!n1(V1), !zn(V2)\n" +
//            "!pd(V1), !s3(V1)\n" +
//            "!nam(V1), !br(V1)\n" +
//            "!car(V1), !ni(V2)\n" +
//            "!p3(V1), !cd(V2)\n" +
//            "!eu(V1), !cr(V2)\n" +
//            "!n1(V1), !c1(V1)\n" +
//            "!am_bond(V1, V2), !eu(V1)\n" +
//            "!au(V1), !nam(V2)\n" +
//            "!cu(V1), !eu(V2)\n" +
//            "!zn(V1), !car(V1)\n" +
//            "!3_bond(V2, V1), !n3(V1)\n" +
//            "!c1(V1), !car(V1)\n" +
//            "!ar_bond(V2, V2)\n" +
//            "!n2(V1), !hg(V2)\n" +
//            "!nar(V1), !o2(V1)\n" +
//            "!ru(V1), !au(V2)\n" +
//            "!pd(V1), !cl(V1)\n" +
//            "!so(V1), !eu(V1)\n" +
//            "!nar(V1), !se(V1)\n" +
//            "!2_bond(V1, V2), !n1(V2)\n" +
//            "!pb(V1), !s2(V1)\n" +
//            "!zn(V1), !zn(V2)\n" +
//            "!ge(V1), !nar(V2)\n" +
//            "!n1(V1), !n4(V1)\n" +
//            "!n3(V1), !n2(V1)\n" +
//            "!ar_bond(V1, V2), !c2(V2)\n" +
//            "!pd(V1), !npl3(V1)\n" +
//            "!eu(V1), !n1(V2)\n" +
//            "!zn(V1), !c1(V1)\n" +
//            "!cd(V1), !o2(V1)\n" +
//            "!pd(V1), !fe(V1)\n" +
//            "!cd(V1), !se(V1)\n" +
//            "!eu(V1), !cl(V2)\n" +
//            "!ni(V1), !fe(V2)\n" +
//            "!au(V1), !cu(V1)\n" +
//            "!sn(V1), !i(V2)\n" +
//            "!car(V1), !zn(V2)\n" +
//            "!sn(V1), !cu(V2)\n" +
//            "!ar_bond(V1, V2), !p3(V1)\n" +
//            "!cu(V1), !zn(V2)\n" +
//            "!pd(V1), !se(V2)\n" +
//            "!so(V1), !n3(V2)\n" +
//            "!npl3(V1), !fe(V2)\n" +
//            "!sn(V1), !cr(V2)\n" +
//            "!oco2(V1), !npl3(V1)\n" +
//            "!2_bond(V1, V2), !cr(V2)\n" +
//            "!i(V1), !se(V1)\n" +
//            "!ni(V1), !so2(V2)\n" +
//            "!o3(V1), !ni(V1)\n" +
//            "!so(V1), !ru(V2)\n" +
//            "!pd(V1), !3_bond(V1, V3)\n" +
//            "!3_bond(V2, V1), !eu(V1)\n" +
//            "!zn(V1), !n4(V1)\n" +
//            "!pb(V1), !p3(V1)\n" +
//            "!au(V1), !cr(V1)\n" +
//            "!2_bond(V1, V3), !cd(V1)\n" +
//            "!2_bond(V2, V1), !f(V1)\n" +
//            "!ge(V1), !pt(V2)\n" +
//            "!2_bond(V2, V1), !am_bond(V1, V2)\n" +
//            "!2_bond(V1, V2), !am_bond(V1, V2)\n" +
//            "!am_bond(V1, V2), !n3(V2)\n" +
//            "!au(V1), !s3(V1)\n" +
//            "!o3(V1), !eu(V1)\n" +
//            "!pt(V1), !br(V1)\n" +
//            "!hg(V1), !pt(V1)\n" +
//            "!pd(V1), !ni(V1)\n" +
//            "!n4(V1), !nam(V2)\n" +
//            "!so2(V1), !zn(V2)\n" +
//            "!i(V1), !o2(V1)\n" +
//            "!hg(V1), !br(V1)\n" +
//            "!n1(V1), !car(V1)\n" +
//            "!c1(V1), !cu(V1)\n" +
//            "!pb(V1), !pb(V2)\n" +
//            "!am_bond(V2, V1), !sn(V1)\n" +
//            "!2_bond(V1, V3), !i(V1)\n" +
//            "!f(V1), !o2(V1)\n" +
//            "!sn(V1), !n4(V2)\n" +
//            "!ge(V1), !as(V2)\n" +
//            "!o3(V1), !npl3(V1)\n" +
//            "!oco2(V1), !ni(V1)\n" +
//            "!f(V1), !se(V1)\n" +
//            "!n1(V1), !zn(V1)\n" +
//            "!so(V1), !sn(V1)\n" +
//            "!eu(V1), !fe(V2)\n" +
//            "!oco2(V1), !cl(V1)\n" +
//            "!p3(V1), !so(V2)\n" +
//            "!c1(V1), !npl3(V1)\n" +
//            "!car(V1), !cu(V1)\n" +
//            "!s3(V1), !fe(V1)\n" +
//            "!ar_bond(V1, V2), !npl3(V2)\n" +
//            "!so(V1), !n3(V1)\n" +
//            "!n4(V1), !sn(V1)\n" +
//            "!se(V2), !am_bond(V1, V2)\n" +
//            "!ar_bond(V1, V2), !nam(V1)\n" +
//            "!sn(V1), !ni(V2)\n" +
//            "!i(V1), !c3(V1)\n" +
//            "!am_bond(V1, V2), !fe(V1)\n" +
//            "!ni(V1), !cu(V1)\n" +
//            "!zn(V1), !eu(V1)\n" +
//            "!2_bond(V1, V3), !f(V1)\n" +
//            "!oco2(V1), !fe(V1)\n" +
//            "!n4(V1), !3_bond(V1, V3)\n" +
//            "!am_bond(V1, V2), !cl(V1)\n" +
//            "!so(V1), !hg(V2)\n" +
//            "!cd(V1), !as(V2)\n" +
//            "!au(V1), !se(V1)\n" +
//            "!fe(V1), !cl(V1)\n" +
//            "!n1(V1), !nar(V1)\n" +
//            "!au(V1), !o2(V1)\n" +
//            "!so2(V1), !cr(V2)\n" +
//            "!npl3(V1), !zn(V2)\n" +
//            "!am_bond(V2, V1), !car(V1)\n" +
//            "!hg(V1), !n2(V1)\n" +
//            "!n2(V1), !br(V1)\n" +
//            "!o3(V1), !c3(V1)\n" +
//            "!n1(V1), !sn(V1)\n" +
//            "!o3(V1), !2_bond(V1, V3)\n" +
//            "!f(V1), !c3(V1)\n" +
//            "!hg(V2), !ar_bond(V1, V2)\n" +
//            "!cd(V1), !c2(V2)\n" +
//            "!s2(V1), !pd(V2)\n" +
//            "!o3(V1), !so2(V1)\n" +
//            "!c1(V1), !se(V1)\n" +
//            "!cd(V1), !s2(V1)\n" +
//            "!pd(V1), !ru(V1)\n" +
//            "!se(V1), !zn(V2)\n" +
//            "!pd(V1), !br(V2)\n" +
//            "!n3(V1), !nam(V1)\n" +
//            "!3_bond(V2, V1), !car(V1)\n" +
//            "!ar_bond(V1, V2), !n3(V1)\n" +
//            "!pt(V1), !n2(V1)\n" +
//            "!i(V1), !hg(V2)\n" +
//            "!ni(V1), !cr(V1)\n" +
//            "!s3(V1), !se(V2)\n" +
//            "!zn(V1), !nar(V1)\n" +
//            "!fe(V1), !se(V2)\n" +
//            "!car(V1), !cl(V1)\n" +
//            "!oco2(V1), !cu(V1)\n" +
//            "!3_bond(V2, V1), !fe(V1)\n" +
//            "!ge(V1), !cd(V2)\n" +
//            "!3_bond(V2, V1), !s2(V1)\n" +
//            "!c1(V1), !o2(V1)\n" +
//            "!eu(V1), !cu(V1)\n" +
//            "!f(V1), !so2(V1)\n" +
//            "!au(V1), !npl3(V1)\n" +
//            "!3_bond(V2, V1), !cl(V1)\n" +
//            "!pd(V1), !2_bond(V2, V1)\n" +
//            "!zn(V1), !sn(V1)\n" +
//            "!ni(V1), !o2(V2)\n" +
//            "!cd(V1), !nam(V2)\n" +
//            "!o3(V1), !hg(V2)\n" +
//            "!i(V1), !ru(V1)\n" +
//            "!ni(V1), !s3(V1)\n" +
//            "!oco2(V1), !s3(V1)\n" +
//            "!cu(V1), !ru(V2)\n" +
//            "!ni(V1), !fe(V1)\n" +
//            "!2_bond(V2, V1), !o3(V1)\n" +
//            "!car(V1), !s3(V1)\n" +
//            "!ru(V1), !f(V1)\n" +
//            "!pd(V1), !so2(V1)\n" +
//            "!cr(V1), !eu(V1)\n" +
//            "!n1(V1), !se(V1)\n" +
//            "!i(V1), !so2(V1)\n" +
//            "!i(V1), !br(V2)\n" +
//            "!nar(V1), !cr(V1)\n" +
//            "!oco2(V1), !cr(V1)\n" +
//            "!ni(V1), !cl(V1)\n" +
//            "!pd(V1), !c3(V1)\n" +
//            "!cd(V1), !p3(V1)\n" +
//            "!as(V1), !pb(V1)\n" +
//            "!3_bond(V2, V1), !p3(V1)\n" +
//            "!n4(V1), !se(V1)\n" +
//            "!p3(V1), !pd(V2)\n" +
//            "!nar(V1), !cu(V1)\n" +
//            "!pb(V1), !c2(V1)\n" +
//            "!o3(V1), !n3(V1)\n" +
//            "!n4(V1), !o2(V1)\n" +
//            "!eu(V1), !s3(V1)\n" +
//            "!pb(V1), !ge(V1)\n" +
//            "!3_bond(V1, V2), !cr(V1)\n" +
//            "!am_bond(V1, V2), !ni(V1)\n" +
//            "!car(V1), !cr(V1)\n" +
//            "1_bond(V1, V2), !cr(V1)\n" +
//            "!c1(V1), !ni(V1)\n" +
//            "!zn(V1), !fe(V1)\n" +
//            "!au(V1), !eu(V1)\n" +
//            "!ni(V1), !ni(V2)\n" +
//            "!eu(V1), !s3(V2)\n" +
//            "!cd(V1), !hg(V2)\n" +
//            "!ar_bond(V1, V2), !ru(V1)\n" +
//            "!nar(V1), !s3(V1)\n" +
//            "!eu(V1), !zn(V2)\n" +
//            "!3_bond(V2, V1), !cu(V1)\n" +
//            "!zn(V1), !cl(V1)\n" +
//            "!ru(V1), !cr(V2)\n" +
//            "!zn(V1), !oco2(V1)\n" +
//            "!npl3(V1), !cu(V1)\n" +
//            "!cr(V1), !cu(V1)\n" +
//            "!ge(V1), !oco2(V2)\n" +
//            "!cu(V1), !3_bond(V1, V3)\n" +
//            "!n2(V1), !pb(V2)\n" +
//            "!ru(V1), !n1(V2)\n" +
//            "!am_bond(V1, V2), !cu(V1)\n" +
//            "!3_bond(V1, V2), !pb(V2)\n" +
//            "!sn(V1), !eu(V2)\n" +
//            "!pd(V1), !o2(V1)\n" +
//            "!nar(V1), !fe(V1)\n" +
//            "!pd(V1), !se(V1)\n" +
//            "!cu(V1), !o2(V2)\n" +
//            "!ar_bond(V1, V2), !br(V2)\n" +
//            "!n3(V1), !cd(V1)\n" +
//            "!nar(V1), !npl3(V1)\n" +
//            "!s2(V1), !au(V2)\n" +
//            "!so(V1), !ru(V1)\n" +
//            "!car(V1), !fe(V1)\n" +
//            "!c1(V1), !eu(V1)\n" +
//            "!c1(V1), !ar_bond(V1, V3)\n" +
//            "!nar(V1), !cl(V1)\n" +
//            "!n4(V1), !ni(V1)\n" +
//            "!s3(V1), !cu(V1)\n" +
//            "!pt(V1), !nar(V2)\n" +
//            "!am_bond(V1, V2), !c3(V2)\n" +
//            "!au(V1), !2_bond(V1, V2)\n" +
//            "!car(V1), !eu(V1)\n" +
//            "!zn(V1), !ni(V1)\n" +
//            "!n4(V1), !oco2(V1)\n" +
//            "!zn(V1), !npl3(V1)\n" +
//            "!n3(V1), !i(V1)\n" +
//            "!cr(V1), !s3(V1)\n" +
//            "!au(V1), !sn(V1)\n" +
//            "!am_bond(V1, V2), !o2(V2)\n" +
//            "!so2(V1), !fe(V2)\n" +
//            "!am_bond(V1, V2), !cr(V1)\n" +
//            "!cu(V1), !so2(V2)\n" +
//            "!oco2(V1), !car(V1)\n" +
//            "!n1(V1), !npl3(V1)\n" +
//            "!nar(V1), !3_bond(V1, V3)\n" +
//            "!am_bond(V1, V2), !s3(V1)\n" +
//            "!cu(V1), !cl(V1)\n" +
//            "!cu(V1), !fe(V1)\n" +
//            "!hg(V1), !as(V1)\n" +
//            "!pt(V1), !as(V1)\n" +
//            "!n4(V1), !eu(V1)\n" +
//            "!n1(V1), !ni(V1)\n" +
//            "!f(V1), !hg(V2)\n" +
//            "!nam(V1), !s2(V1)\n" +
//            "!2_bond(V1, V2), !zn(V2)\n" +
//            "!car(V1), !3_bond(V1, V3)\n" +
//            "!c1(V1), !ru(V2)\n" +
//            "!c3(V1), !cr(V2)\n" +
//            "!as(V1), !br(V1)\n" +
//            "!so2(V1), !n4(V2)\n" +
//            "!cr(V1), !cl(V1)\n" +
//            "!n4(V1), !npl3(V1)\n" +
//            "!pd(V1), !2_bond(V1, V3)\n" +
//            "!pt(V1), !ge(V1)\n" +
//            "!ar_bond(V1, V2), !p3(V2)\n" +
//            "!so(V1), !so2(V1)\n" +
//            "!ge(V1), !n2(V2)\n" +
//            "!hg(V1), !c2(V1)\n" +
//            "!br(V1), !ge(V1)\n" +
//            "!cr(V1), !fe(V1)\n" +
//            "!nar(V1), !ni(V1)\n" +
//            "!n4(V1), !ru(V2)\n" +
//            "!so(V1), !c3(V1)\n" +
//            "!n3(V1), !f(V1)\n" +
//            "!hg(V1), !ge(V1)\n" +
//            "!nam(V1), !p3(V1)\n" +
//            "!cu(V1), !se(V2)\n" +
//            "!1_bond(V2, V1), !s2(V1)\n" +
//            "!c2(V1), !br(V1)\n" +
//            "!s3(V1), !cl(V1)\n" +
//            "!c1(V1), !sn(V1)\n" +
//            "!car(V1), !ni(V1)\n" +
//            "!fe(V1), !o2(V2)\n" +
//            "!sn(V1), !zn(V2)\n" +
//            "!pt(V1), !c2(V1)\n" +
//            "!n1(V1), !eu(V1)\n" +
//            "!ar_bond(V1, V2), !c3(V1)\n" +
//            "!s2(V1), !so(V2)\n" +
//            "!n1(V1), !oco2(V1)\n" +
//            "!o3(V1), !ru(V1)\n" +
//            "!2_bond(V2, V1), !cu(V1)\n" +
//            "!so(V1), !pb(V2)\n" +
//            "!ar_bond(V1, V2), !se(V1)\n" +
//            "!am_bond(V1, V2), !nar(V2)\n" +
//            "!car(V1), !sn(V1)\n" +
//            "!au(V1), !oco2(V1)\n" +
//            "!hg(V1), !cd(V1)\n" +
//            "!au(V1), !n3(V1)\n" +
//            "!f(V1), !s2(V1)\n" +
//            "!sn(V1), !se(V2)\n" +
//            "!nar(V1), !as(V2)\n" +
//            "!as(V1), !n2(V1)\n" +
//            "!n4(V1), !c3(V1)\n" +
//            "!se(V1), !cl(V1)\n" +
//            "!ni(V1), !ru(V2)\n" +
//            "!pd(V1), !as(V2)\n" +
//            "!s3(V1), !se(V1)\n" +
//            "!am_bond(V1, V2), !ni(V2)\n" +
//            "!cd(V1), !br(V1)\n" +
//            "!cd(V1), !pt(V1)\n" +
//            "!oco2(V2), !ar_bond(V1, V2)\n" +
//            "!pt(V1), !pd(V2)\n" +
//            "!2_bond(V1, V2), !cr(V1)\n" +
//            "!zn(V1), !ru(V1)\n" +
//            "!s2(V1), !fe(V2)\n" +
//            "!i(V1), !p3(V1)\n" +
//            "!o3(V1), !s2(V1)\n" +
//            "!s3(V1), !o2(V1)\n" +
//            "!fe(V1), !o2(V1)\n" +
//            "!n2(V1), !ge(V1)\n" +
//            "!eu(V1), !nam(V2)\n" +
//            "!f(V1), !p3(V1)\n" +
//            "!car(V1), !ru(V2)\n" +
//            "!npl3(V1), !nam(V1)\n" +
//            "!zn(V1), !3_bond(V1, V2)\n" +
//            "!fe(V1), !se(V1)\n" +
//            "!o2(V1), !cl(V1)\n" +
//            "!s2(V1), !i(V2)\n" +
//            "!am_bond(V2, V1), !c1(V1)\n" +
//            "!ni(V1), !n3(V2)\n" +
//            "!1_bond(V1, V3), !s2(V1)\n" +
//            "!p3(V1), !fe(V2)\n" +
//            "!n1(V1), !c3(V1)\n" +
//            "!c1(V1), !n3(V1)\n" +
//            "!c2(V1), !n2(V1)\n" +
//            "!am_bond(V1, V2), !zn(V2)\n" +
//            "!ni(V1), !sn(V1)\n" +
//            "!p3(V1), !so2(V2)\n" +
//            "!eu(V1), !ru(V2)\n" +
//            "!o3(V1), !p3(V1)\n" +
//            "!oco2(V1), !c1(V1)\n" +
//            "!n1(V1), !am_bond(V1, V2)\n" +
//            "!ru(V1), !cu(V1)\n" +
//            "!eu(V1), !n3(V2)\n" +
//            "!i(V1), !as(V2)\n" +
//            "!n4(V1), !hg(V2)\n" +
//            "!pb(V1), !npl3(V1)\n" +
//            "!ar_bond(V1, V2), !1_bond(V1, V2)\n" +
//            "!ar_bond(V1, V2), !1_bond(V2, V1)\n" +
//            "!zn(V1), !am_bond(V1, V2)\n" +
//            "!ru(V1), !so2(V1)\n" +
//            "!pd(V1), !s2(V1)\n" +
//            "!zn(V1), !c3(V1)\n" +
//            "!se(V1), !se(V2)\n" +
//            "!eu(V1), !sn(V1)\n" +
//            "!n4(V1), !n3(V1)\n" +
//            "!nar(V1), !s2(V1)\n" +
//            "!f(V1), !as(V2)\n" +
//            "!n2(V1), !am_bond(V1, V3)\n" +
//            "!ru(V1), !o2(V2)\n" +
//            "!cu(V1), !br(V2)\n" +
//            "!sn(V1), !ru(V2)\n" +
//            "!npl3(V1), !cd(V2)\n" +
//            "!n1(V1), !n3(V1)\n" +
//            "!ni(V1), !o2(V1)\n" +
//            "!cu(V1), !p3(V2)\n" +
//            "!i(V1), !s2(V1)\n" +
//            "!cd(V1), !cd(V2)\n" +
//            "!n1(V1), !2_bond(V1, V2)\n" +
//            "!n4(V1), !am_bond(V1, V2)\n" +
//            "!ru(V1), !s3(V1)\n" +
//            "!cr(V1), !ru(V1)\n" +
//            "!zn(V1), !2_bond(V1, V2)\n" +
//            "!o3(V1), !pb(V2)\n" +
//            "!pd(V1), !p3(V1)\n" +
//            "!cu(V1), !s2(V2)\n" +
//            "!au(V1), !as(V2)\n" +
//            "!ni(V1), !se(V1)\n" +
//            "!nar(V1), !p3(V1)\n" +
//            "!pt(V1), !cu(V2)\n" +
//            "!cu(V1), !sn(V1)\n" +
//            "!pd(V1), !hg(V2)\n" +
//            "!cu(V1), !n3(V2)\n" +
//            "!npl3(V1), !n2(V1)\n" +
//            "!nar(V1), !pb(V2)\n" +
//            "!pt(V1), !au(V2)\n" +
//            "!au(V1), !ru(V1)\n" +
//            "!n2(V1), !cd(V2)\n" +
//            "!zn(V1), !se(V1)\n" +
//            "!1_bond(V2, V2)\n" +
//            "!ni(V1), !se(V2)\n" +
//            "!br(V1), !au(V2)\n" +
//            "!zn(V1), !o2(V1)\n" +
//            "!s3(V1), !ru(V2)\n" +
//            "!n1(V1), !o2(V1)\n" +
//            "!3_bond(V1, V2), !o2(V2)\n" +
//            "!hg(V1), !au(V2)\n" +
//            "!sn(V1), !so2(V1)\n" +
//            "!cd(V1), !pb(V2)\n" +
//            "!eu(V1), !cl(V1)\n" +
//            "!3_bond(V2, V1), !s3(V1)\n" +
//            "!so(V1), !as(V2)\n" +
//            "!2_bond(V1, V3), !fe(V1)\n" +
//            "!2_bond(V2, V1), !ni(V1)\n" +
//            "!ge(V1), !am_bond(V1, V3)\n" +
//            "!eu(V1), !fe(V1)\n" +
//            "!npl3(V1), !3_bond(V1, V3)\n" +
//            "!hg(V1), !oco2(V2)\n" +
//            "!3_bond(V2, V2)\n" +
//            "!s2(V1), !n4(V2)\n" +
//            "!cr(V1), !sn(V1)\n" +
//            "!pb(V1), !n2(V1)\n" +
//            "!3_bond(V1, V2), !ar_bond(V1, V2)\n" +
//            "!ar_bond(V1, V2), !3_bond(V2, V1)\n" +
//            "!s3(V1), !sn(V1)\n" +
//            "!so(V1), !s2(V1)\n" +
//            "!pt(V1), !so(V2)\n" +
//            "!pd(V1), !n3(V1)\n" +
//            "!pd(V1), !oco2(V1)\n" +
//            "!br(V1), !so(V2)\n" +
//            "!fe(V1), !ru(V2)\n" +
//            "!pt(V1), !i(V1)\n" +
//            "!cd(V1), !nam(V1)\n" +
//            "!s2(V1), !cr(V2)\n" +
//            "!c1(V1), !ru(V1)\n" +
//            "!ru(V1), !zn(V2)\n" +
//            "!hg(V1), !i(V1)\n" +
//            "!pd(V1), !am_bond(V1, V2)\n" +
//            "!car(V1), !o2(V1)\n" +
//            "!ar_bond(V1, V2), !eu(V1)\n" +
//            "!3_bond(V1, V3), !n2(V1)\n" +
//            "!car(V1), !se(V1)\n" +
//            "!ar_bond(V1, V2), !2_bond(V1, V2)\n" +
//            "!ar_bond(V1, V2), !2_bond(V2, V1)\n" +
//            "!au(V1), !c3(V1)\n" +
//            "!am_bond(V1, V2), !eu(V2)\n" +
//            "!car(V1), !ru(V1)\n" +
//            "!i(V1), !pb(V2)\n" +
//            "!npl3(V1), !ge(V1)\n" +
//            "!n4(V1), !ru(V1)\n" +
//            "!eu(V1), !se(V2)\n" +
//            "!c1(V1), !br(V2)\n" +
//            "!n1(V1), !1_bond(V1, V2)\n" +
//            "!3_bond(V1, V2), !c2(V2)\n" +
//            "!ar_bond(V2, V1), !sn(V1)\n" +
//            "!npl3(V1), !c2(V1)\n" +
//            "!2_bond(V2, V1), !fe(V1)\n" +
//            "!f(V1), !pb(V2)\n" +
//            "!p3(V1), !cr(V2)\n" +
//            "!cu(V1), !o2(V1)\n" +
//            "!fe(V1), !sn(V1)\n" +
//            "!fe(V1), !n3(V2)\n" +
//            "!as(V1), !ge(V1)\n" +
//            "!c1(V1), !s2(V2)\n" +
//            "!cu(V1), !se(V1)\n" +
//            "!sn(V1), !cl(V1)\n" +
//            "!3_bond(V1, V2), !as(V2)\n" +
//            "!as(V1), !c2(V1)\n" +
//            "!se(V1), !so2(V1)\n" +
//            "!s2(V1), !n1(V2)\n" +
//            "!am_bond(V1, V2), !s3(V2)\n" +
//            "!o2(V1), !so2(V1)\n" +
//            "!cr(V1), !se(V1)\n" +
//            "!au(V1), !am_bond(V1, V2)\n" +
//            "!p3(V1), !n1(V2)\n" +
//            "!n1(V1), !ru(V1)\n" +
//            "!ni(V1), !eu(V1)\n" +
//            "!ar_bond(V1, V2), !ru(V2)\n" +
//            "!c2(V1), !ge(V1)\n" +
//            "!3_bond(V1, V3), !ge(V1)\n" +
//            "!2_bond(V1, V3), !cu(V1)\n" +
//            "!c1(V1), !c3(V1)\n" +
//            "!so(V1), !p3(V1)\n" +
//            "!cr(V1), !o2(V1)\n" +
//            "!ar_bond(V1, V2), !n3(V2)\n" +
//            "!o3(V1), !oco2(V1)\n" +
//            "!as(V1), !npl3(V1)\n" +
//            "!so2(V1), !se(V2)\n" +
//            "!cd(V1), !as(V1)\n" +
//            "!ge(V1), !pd(V2)\n" +
//            "!3_bond(V2, V1), !ni(V1)\n" +
//            "!2_bond(V1, V2), !n3(V2)\n" +
//            "!n3(V1), !cl(V1)\n" +
//            "!pt(V1), !f(V2)\n" +
//            "!car(V1), !c3(V1)\n" +
//            "!pd(V1), !pt(V1)\n" +
//            "!am_bond(V1, V2), !n1(V2)\n" +
//            "!n4(V1), !p3(V1)\n" +
//            "!so(V1), !pb(V1)\n" +
//            "!ar_bond(V1, V2), !fe(V1)\n" +
//            "!fe(V1), !hg(V2)\n" +
//            "!am_bond(V1, V2), !cr(V2)\n" +
//            "!c1(V1), !ge(V2)\n" +
//            "!i(V1), !cd(V2)\n" +
//            "!cd(V1), !c2(V1)\n" +
//            "!cd(V1), !ge(V1)\n" +
//            "!ni(V1), !p3(V2)\n" +
//            "!n1(V1), !s2(V1)\n" +
//            "!car(V1), !so2(V1)\n" +
//            "!2_bond(V2, V1), !1_bond(V1, V2)\n" +
//            "!2_bond(V1, V2), !1_bond(V1, V2)\n" +
//            "!ni(V1), !s2(V2)\n" +
//            "!pd(V1), !br(V1)\n" +
//            "!n3(V1), !fe(V1)\n" +
//            "!pd(V1), !hg(V1)\n" +
//            "!ru(V1), !eu(V1)\n" +
//            "!zn(V1), !c2(V2)\n" +
//            "!n1(V1), !p3(V1)\n" +
//            "!2_bond(V1, V3), !ni(V1)\n" +
//            "!c1(V1), !pb(V2)\n" +
//            "!eu(V1), !br(V2)\n" +
//            "!2_bond(V1, V2), !ru(V2)\n" +
//            "!ru(V1), !ru(V2)\n" +
//            "!cl(V2), !am_bond(V1, V2)\n" +
//            "!fe(V1), !so2(V1)\n" +
//            "!ni(V1), !so2(V1)\n" +
//            "!ar_bond(V1, V3), !n2(V1)\n" +
//            "!cl(V1), !so2(V1)\n" +
//            "!f(V1), !cd(V2)\n" +
//            "!ni(V1), !c3(V1)\n" +
//            "!am_bond(V1, V2), !n4(V2)\n" +
//            "!ar_bond(V1, V2), !s3(V1)\n" +
//            "!2_bond(V1, V3), !eu(V1)\n" +
//            "!npl3(V1), !au(V2)\n" +
//            "!nar(V1), !ru(V1)\n" +
//            "!so(V1), !am_bond(V1, V2)\n" +
//            "!zn(V1), !nam(V2)\n" +
//            "!so(V1), !oco2(V1)\n" +
//            "!pb(V1), !nam(V1)\n" +
//            "!zn(V1), !s2(V1)\n" +
//            "!cu(V1), !as(V2)\n" +
//            "!oco2(V1), !nam(V1)\n" +
//            "!ar_bond(V1, V2), !cl(V1)\n" +
//            "!eu(V1), !p3(V2)\n" +
//            "!o2(V1), !se(V1)\n" +
//            "!i(V1), !br(V1)\n" +
//            "!oco2(V1), !i(V1)\n" +
//            "!o3(V1), !am_bond(V1, V2)\n" +
//            "!ru(V1), !sn(V1)\n" +
//            "!ni(V1), !hg(V2)\n" +
//            "!2_bond(V2, V1), !eu(V1)\n" +
//            "!car(V1), !n3(V1)\n" +
//            "!eu(V1), !c3(V1)\n" +
//            "!n3(V1), !ni(V1)\n" +
//            "!ar_bond(V2, V1), !ni(V1)\n" +
//            "!hg(V1), !f(V1)\n" +
//            "!cr(V1), !as(V2)\n" +
//            "!3_bond(V1, V2), !nam(V2)\n" +
//            "!cr(V1), !so2(V1)\n" +
//            "!pt(V1), !eu(V2)\n" +
//            "!cd(V1), !n2(V1)\n" +
//            "!pt(V1), !f(V1)\n" +
//            "!f(V1), !br(V1)\n" +
//            "!n2(V1), !pd(V2)\n" +
//            "!n4(V1), !pb(V2)\n" +
//            "!zn(V1), !p3(V1)\n" +
//            "!pd(V1), !cd(V2)\n" +
//            "!pt(V1), !s3(V2)\n" +
//            "!sn(V1), !br(V2)\n" +
//            "!sn(V1), !p3(V2)\n" +
//            "!zn(V1), !pb(V2)\n" +
//            "1_bond(V1, V3), !cd(V1)\n" +
//            "!nar(V1), !c3(V1)\n" +
//            "!cu(V1), !s2(V1)\n" +
//            "!hg(V2), !3_bond(V1, V2)\n" +
//            "!oco2(V1), !f(V1)\n" +
//            "!nar(V1), !so2(V1)\n" +
//            "!ar_bond(V1, V3), !ge(V1)\n" +
//            "!cr(V1), !c2(V2)\n" +
//            "!cr(V1), !s2(V1)\n" +
//            "!so(V1), !as(V1)\n" +
//            "!am_bond(V1, V2), !fe(V2)\n" +
//            "!cu(V1), !so2(V1)\n" +
//            "!eu(V1), !hg(V2)\n" +
//            "!ge(V1), !npl3(V2)\n" +
//            "!au(V1), !br(V1)\n" +
//            "!n1(V1), !pb(V2)\n" +
//            "!au(V1), !hg(V1)\n" +
//            "!3_bond(V2, V1), !sn(V1)\n" +
//            "!ge(V1), !cu(V2)\n" +
//            "!so2(V1), !ru(V2)\n" +
//            "!au(V1), !pt(V1)\n" +
//            "!o3(V1), !pb(V1)\n" +
//            "!se(V2), !ar_bond(V1, V2)\n" +
//            "!c3(V1), !ru(V2)\n" +
//            "!pt(V1), !i(V2)\n" +
//            "!nam(V1), !ge(V1)\n" +
//            "!am_bond(V1, V2), !cd(V1)\n" +
//            "!hg(V1), !cr(V2)\n" +
//            "!3_bond(V1, V2), !1_bond(V1, V2)\n" +
//            "!3_bond(V2, V1), !1_bond(V1, V2)\n" +
//            "!s3(V1), !br(V2)\n" +
//            "!cu(V1), !c3(V1)\n" +
//            "!ge(V1), !au(V2)\n" +
//            "!pd(V1), !pb(V2)\n" +
//            "!br(V1), !cr(V2)\n" +
//            "!pt(V1), !cr(V2)\n" +
//            "!s2(V1), !eu(V2)\n" +
//            "!pt(V1), !n1(V2)\n" +
//            "!as(V1), !nam(V1)\n" +
//            "!f(V2), !am_bond(V1, V2)\n" +
//            "!3_bond(V2, V1), !c3(V1)\n" +
//            "!ru(V1), !fe(V1)\n" +
//            "!eu(V1), !se(V1)\n" +
//            "!cr(V1), !nam(V2)\n" +
//            "!c2(V1), !nam(V1)\n" +
//            "!zn(V1), !n3(V1)\n" +
//            "!so(V1), !pt(V1)\n" +
//            "!pt(V1), !n4(V2)\n" +
//            "!3_bond(V2, V1), !so2(V1)\n" +
//            "!ru(V1), !cl(V1)\n" +
//            "!so(V1), !br(V1)\n" +
//            "!hg(V1), !so(V1)\n" +
//            "!ge(V1), !o3(V2)\n" +
//            "!cr(V1), !c3(V1)\n" +
//            "!s2(V1), !zn(V2)\n" +
//            "!n1(V1), !ar_bond(V1, V2)\n" +
//            "!br(V1), !n1(V2)\n" +
//            "1_bond(V1, V2), !ru(V1)\n" +
//            "!c1(V1), !as(V2)\n" +
//            "!se(V1), !ru(V2)\n" +
//            "!au(V1), !s2(V1)\n" +
//            "!cd(V1), !pb(V1)\n" +
//            "!eu(V1), !o2(V1)\n" +
//            "!eu(V1), !so2(V1)\n" +
//            "!ge(V1), !so(V2)\n" +
//            "!c1(V1), !pt(V2)\n" +
//            "!oco2(V1), !cd(V1)\n" +
//            "!as(V1), !i(V1)\n" +
//            "!nar(V1), !n3(V1)\n" +
//            "!cd(V1), !npl3(V1)\n" +
//            "!cu(V1), !hg(V2)\n" +
//            "!fe(V1), !br(V2)\n" +
//            "!am_bond(V1, V2), !pd(V2)\n" +
//            "!i(V1), !ge(V1)\n" +
//            "!zn(V1), !ar_bond(V1, V2)\n" +
//            "!s3(V1), !c3(V1)\n" +
//            "!s3(V1), !so2(V1)\n" +
//            "!3_bond(V2, V1), !se(V1)\n" +
//            "!3_bond(V1, V2), !o2(V1)\n" +
//            "!i(V1), !pb(V1)\n" +
//            "!am_bond(V1, V2), !f(V1)\n" +
//            "!n2(V1), !nam(V1)\n" +
//            "!ar_bond(V1, V2), !cu(V1)\n" +
//            "!c1(V1), !s2(V1)\n" +
//            "!n4(V1), !as(V2)\n" +
//            "!n2(V1), !au(V2)\n" +
//            "!se(V1), !sn(V1)\n" +
//            "!o3(V1), !pt(V1)\n" +
//            "!se(V1), !n3(V2)\n" +
//            "!au(V1), !p3(V1)\n" +
//            "!n3(V1), !cu(V1)\n" +
//            "!c3(V1), !cl(V1)\n" +
//            "!o3(V1), !br(V1)\n" +
//            "!n3(V1), !cr(V1)\n" +
//            "!hg(V1), !o3(V1)\n" +
//            "!am_bond(V1, V2), !i(V1)\n" +
//            "!fe(V1), !c3(V1)\n" +
//            "!cd(V1), !3_bond(V1, V3)\n" +
//            "!n4(V1), !s2(V1)\n" +
//            "!n3(V1), !s3(V1)\n" +
//            "!ar_bond(V1, V2), !c3(V2)\n" +
//            "!am_bond(V1, V2), !i(V2)\n" +
//            "!zn(V1), !as(V2)\n" +
//            "!pt(V1), !fe(V2)\n" +
//            "!ar_bond(V1, V2), !cr(V1)\n" +
//            "!cu(V2), !am_bond(V1, V2)\n" +
//            "!n1(V1), !as(V2)\n" +
//            "!n2(V1), !so(V2)\n" +
//            "!npl3(V1), !so(V2)\n" +
//            "!am_bond(V1, V2), !npl3(V2)\n" +
//            "!f(V1), !pb(V1)\n" +
//            "!ni(V1), !ru(V1)\n" +
//            "!o2(V1), !sn(V1)\n" +
//            "!au(V1), !pb(V2)\n" +
//            "!ni(V1), !br(V2)\n" +
//            "!p3(V1), !zn(V2)\n" +
//            "!c1(V1), !p3(V1)\n" +
//            "!cl(V1), !hg(V2)";
//
//    public static void main(String[] args){
//        Set<IsoClauseWrapper> constraints = new HashSet<>();
//
//        long parsingTime = System.nanoTime();
//        for (String s : queriedMemorized.split("\n")) { // parentsCount
//            if(s.trim().length() > 0){
//                constraints.add(new IsoClauseWrapper(Clause.parse(s)));
//            }
//        }
//        parsingTime = System.nanoTime() - parsingTime;
//
//        long maximal = System.nanoTime();
//        int numVars = 0;
//        Set<Clause> retVal = new HashSet<Clause>();
//        for (IsoClauseWrapper icw : constraints) {
//            retVal.add(icw.getOriginalClause());
//            numVars = Math.max(numVars, icw.getOriginalClause().variables().size());
//        }
//        maximal = System.nanoTime() - maximal;
//
//        long start = System.nanoTime();
//        List<Clause> theory = TheorySimplifier.simplify(retVal, numVars + 1);
//        System.out.println("theory simplifier\t" + ((System.nanoTime() - start) / 1000000));
//        System.out.println("parsing          \t" + (parsingTime / 1000000));
//        System.out.println("maximal          \t" + (maximal/ 1000000));
//    }
//}
//
